package ua.nure.serdyuk.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.Message;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.RouteService;
import ua.nure.serdyuk.db.service.StationService;
import ua.nure.serdyuk.db.service.TrainBeanService;
import ua.nure.serdyuk.entity.Route;
import ua.nure.serdyuk.entity.Station;
import ua.nure.serdyuk.entity.bean.TrainBean;

public class FindTrainsCommand implements Command {

	private static final Logger LOG = Logger.getLogger(FindTrainsCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String stationFrom = req.getParameter(Const.STATION_FROM);
		String stationTo = req.getParameter(Const.STATION_TO);
		String date = req.getParameter(Const.DATE);

		HttpSession session = req.getSession();
		ServletContext context = req.getServletContext();

		// req.getSession().invalidate();

		req.setAttribute(Const.STATION_FROM, stationFrom);
		req.setAttribute(Const.STATION_TO, stationTo);
		req.setAttribute(Const.DATE, date);

		LOG.debug("date=" + date);

		// hmm it's checked on client side
		// check for after today only
		if (date == null || date.isEmpty()) {
			req.setAttribute("dateError", "Date is incorrect");
			session.setAttribute(Const.TRAIN_INFO_BEANS, null);
			return Path.INDEX_VIEW;
		}
		LOG.debug(String.format("Looking for train from %s to %s, date: %s",
				stationFrom, stationTo, date));

		Map<String, Station> stations = getStations(req, stationFrom,
				stationTo);
		Station from = stations.get(stationFrom);
		Station to = stations.get(stationTo);

		if (hasErrors(req, from, to)) {
			session.setAttribute(Const.TRAIN_INFO_BEANS, null);
			return Path.INDEX_VIEW;
		}

		// not session -- WRONG
		session.setAttribute(Const.TRAIN_INFO_BEANS, getTrainBeans(context, from, to, date));

		return Path.INDEX_VIEW;
	}

	private Map<String, Station> getStations(HttpServletRequest req,
			String stationFrom, String stationTo) {
		Map<String, Station> stations = new HashMap<>();

		ServletContext context = req.getServletContext();
		StationService stationService = (StationService) context
				.getAttribute(Const.STATION_SERVICE);

		stations.put(stationFrom, stationService.getByName(stationFrom));
		stations.put(stationTo, stationService.getByName(stationTo));

		return stations;
	}

	private boolean hasErrors(HttpServletRequest req, Station from,
			Station to) {
		boolean error = false;
		if (from == null) {
			error = true;
			req.setAttribute("stationFromError",
					"Station was not found, select from the drop-down list");
			LOG.error(String.format(Message.ERR_STATION_NOT_FOUND, from));
		}

		if (to == null) {
			error = true;
			req.setAttribute("stationToError",
					"Station was not found, select from the drop-down list");
			LOG.error(String.format(Message.ERR_STATION_NOT_FOUND, to));
		}

		return error;
	}

	private List<TrainBean> getTrainBeans(ServletContext context, Station from, Station to,
			String date) {
		RouteService routeService = (RouteService) context.getAttribute(Const.ROUTE_SERVICE);
		TrainBeanService trainBeanService = (TrainBeanService) context.getAttribute(Const.TRAIN_BEAN_SERVICE);
		
		List<Route> routes = routeService.getAllByStationsAndDate(from.getId(),
				to.getId(), date);
		LOG.info(String.format("Routes found ==> ", routes.toString()));

		List<TrainBean> trainBeans = new ArrayList<>();
		for (Route r : routes) {
			trainBeans.add(trainBeanService.getFullInfo(r.getTrainId(),
					from.getId(), to.getId(), r.getId()));
		}

		return trainBeans;
	}

}
