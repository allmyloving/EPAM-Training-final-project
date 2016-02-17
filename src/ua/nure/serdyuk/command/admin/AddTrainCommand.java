package ua.nure.serdyuk.command.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.RouteItemService;
import ua.nure.serdyuk.db.service.StationService;
import ua.nure.serdyuk.db.service.TrainService;
import ua.nure.serdyuk.entity.RouteItem;
import ua.nure.serdyuk.entity.Station;
import ua.nure.serdyuk.entity.Train;
import ua.nure.serdyuk.util.DateUtils;

public class AddTrainCommand implements Command {

	private static final Logger LOG = Logger.getLogger(AddTrainCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String trainTag = req.getParameter("trainTag");
		String price = req.getParameter("trainPrice");

		ServletContext context = req.getServletContext();

		Train train = new Train();
		train.setTag(trainTag);
		train.setPrice(BigDecimal.valueOf(Double.valueOf(price)));

		TrainService trainService = (TrainService) context
				.getAttribute(Const.TRAIN_SERVICE);
		trainService.create(train);

		processRouteItems(req, train.getId());

		return Path.ADD_TRAIN_VIEW_COMMAND;
	}

	private void processRouteItems(HttpServletRequest req, int trainId) {
		String[] stations = req.getParameterValues("stationSelect");
		String[] arrTime = req.getParameterValues("arrTime");
		String[] depTime = req.getParameterValues("depTime");

		StationService stationService = (StationService) req.getServletContext()
				.getAttribute(Const.STATION_SERVICE);

		List<RouteItem> routeItems = new ArrayList<>();
		RouteItem item;
		for (int i = 0; i < stations.length; i++) {
			item = extractRouteItem(req.getServletContext(), arrTime[i], depTime[i], stations[i],i, trainId);
			LOG.debug(item);

			routeItems.add(item);
		}

		RouteItemService routeItemService = (RouteItemService) req
				.getServletContext().getAttribute(Const.ROUTE_ITEM_SERVICE);
		if (routeItemService.createAll(routeItems)) {
			LOG.info("Route items succsessfully created");
		}
	}

	private RouteItem extractRouteItem(ServletContext context, String arrTime, String depTime,
			String stationName, int ordinal, int trainId) {
		StationService stationService = (StationService) context
				.getAttribute(Const.STATION_SERVICE);
		RouteItem item = new RouteItem();
		Date date = DateUtils.extractDate(arrTime,
				Const.CLIENT_TIME_FORMAT);
		LOG.debug("date ==> " + date);
		item.setArrivalTime(date);
		date = DateUtils.extractDate(depTime, Const.CLIENT_TIME_FORMAT);

		Station station = stationService.getByName(stationName);

		item.setOrdinal(ordinal);
		item.setStationId(station.getId());
		item.setTrainId(trainId);
		
		return item;
	}

}
