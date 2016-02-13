package ua.nure.serdyuk.command;

import java.util.ArrayList;
import java.util.List;

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
		String date = req.getParameter("date");

		HttpSession session = req.getSession();

		// req.getSession().invalidate();

		req.setAttribute(Const.STATION_FROM, stationFrom);
		req.setAttribute(Const.STATION_TO, stationTo);
		req.setAttribute("date", date);

		LOG.debug("date=" + date);

		// hmm it's checked on client side
		// check for after today only
		if (date == null || date.isEmpty()) {
			req.setAttribute("dateError", "Date is incorrect");
			session.setAttribute(Const.TRAIN_INFO_BEANS, null);
			return Path.INDEX_VIEW;
		}

		ServletContext context = req.getServletContext();
		RouteService routeService = (RouteService) context
				.getAttribute(Const.ROUTE_SERVICE);
		StationService stationService = (StationService) context
				.getAttribute(Const.STATION_SERVICE);

		LOG.debug(String.format("Looking for train from %s to %s, date: %s",
				stationFrom, stationTo, date));

		Station from = stationService.getByName(stationFrom);
		Station to = stationService.getByName(stationTo);

		boolean error = false;
		if (from == null) {
			error = true;
			req.setAttribute("stationFromError",
					"Station was not found, select from the drop-down list");
			LOG.error(String.format(Message.ERR_STATION_NOT_FOUND, stationFrom));
		}

		if (to == null) {
			error = true;
			req.setAttribute("stationToError",
					"Station was not found, select from the drop-down list");
			LOG.error(String.format(Message.ERR_STATION_NOT_FOUND, stationTo));
		}

		if (error) {
			session.setAttribute(Const.TRAIN_INFO_BEANS, null);
			return Path.INDEX_VIEW;
		}

		List<Route> routes = routeService.getAllByStationsAndDate(from.getId(),
				to.getId(), date);

		LOG.debug("found ==> " + routes);

		TrainBeanService trainInfoService = (TrainBeanService) context
				.getAttribute(Const.TRAIN_INFO_SERVICE);
		List<TrainBean> trainBeans = new ArrayList<>();
		for (Route r : routes) {
			trainBeans.add(trainInfoService.getFullInfo(r.getTrainId(),
					from.getId(), to.getId(), r.getId()));
		}

		// not session -- WRONG
		req.getSession().setAttribute(Const.TRAIN_INFO_BEANS, trainBeans);

		return Path.INDEX_VIEW;
	}

}
