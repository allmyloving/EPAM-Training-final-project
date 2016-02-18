package ua.nure.serdyuk.command.train;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Message;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.RouteService;
import ua.nure.serdyuk.db.service.StationService;
import ua.nure.serdyuk.db.service.TrainBeanService;
import ua.nure.serdyuk.entity.Route;
import ua.nure.serdyuk.entity.Station;
import ua.nure.serdyuk.entity.bean.TrainBean;

public class FindTrainsCommand implements Command {

	private static final Logger LOG = Logger.getLogger(FindTrainsCommand.class);
	

	public static final String ERR_STATION_NOT_FOUND = "Station %s was not found.";

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
			req.setAttribute("dateError", Message.ERR_DATE_INCORRECT);
			session.setAttribute(Const.TRAIN_INFO_BEANS, null);
			return Path.INDEX_VIEW;
		}
		LOG.debug(String.format("Looking for train from %s to %s, date: %s",
				stationFrom, stationTo, date));
		
		StationService stationService = (StationService) context
				.getAttribute(Const.STATION_SERVICE);

		Map<String, Station> stations = stationService.getStations(stationFrom,
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
	
	private boolean hasErrors(HttpServletRequest req, Station from,
			Station to) {
		boolean error = false;
		if (from == null) {
			error = true;
			req.setAttribute("stationFromError",
					Message.STATION_NOT_FOUND);
			LOG.error(String.format(ERR_STATION_NOT_FOUND, from));
		}

		if (to == null) {
			error = true;
			req.setAttribute("stationToError",
					Message.STATION_NOT_FOUND);
			LOG.error(String.format(ERR_STATION_NOT_FOUND, to));
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
