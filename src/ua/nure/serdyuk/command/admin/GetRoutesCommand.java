package ua.nure.serdyuk.command.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.RouteService;
import ua.nure.serdyuk.entity.Route;
import ua.nure.serdyuk.entity.bean.TrainBean;

public class GetRoutesCommand implements Command {

	private static final Logger LOG = Logger.getLogger(GetRoutesCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String dateFrom = req.getParameter("dateFrom");
		String dateTo = req.getParameter("dateTo");

		ServletContext context = req.getServletContext();

		RouteService routeService = (RouteService) context
				.getAttribute(Const.ROUTE_SERVICE);
		List<Route> routes = routeService.getAllByDates(dateFrom, dateTo);

		LOG.info("Routes obtained ==> " + routes);

		@SuppressWarnings("unchecked")
		List<TrainBean> trainBeans = (List<TrainBean>) req.getSession()
				.getAttribute("trainBeans");
		List<TrainBean> trainBeans2 = new ArrayList<>();

		TrainBean tmp;
		for (Route item : routes) {
			tmp = new TrainBean();
			tmp.setTrainId(item.getTrainId());
			TrainBean att = trainBeans.get(trainBeans.indexOf(tmp));
			tmp.setDepDate(item.getDate());
			tmp.setTrainTag(att.getTrainTag());
			tmp.setStationFrom(att.getStationFrom());
			tmp.setStationTo(att.getStationTo());

			trainBeans2.add(tmp);
		}

		req.getSession().setAttribute("routes", trainBeans2);

		return Path.ADD_ROUTE_VIEW_COMMAND;
	}

}
