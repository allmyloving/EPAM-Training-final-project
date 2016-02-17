package ua.nure.serdyuk.command.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.db.service.RouteService;
import ua.nure.serdyuk.entity.Route;
import ua.nure.serdyuk.util.DateUtils;

public class AddRouteCommand implements Command {

	private static final Logger LOG = Logger.getLogger(AddRouteCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String trainIdStr = req.getParameter("trainId");
		String date = req.getParameter("date");

		int trainId = Integer.valueOf(trainIdStr);

		RouteService service = (RouteService) req.getServletContext()
				.getAttribute(Const.ROUTE_SERVICE);
		Route route = new Route();
		route.setTrainId(trainId);
		route.setDate(DateUtils.extractDate(date, Const.CLIENT_DATE_FORMAT));

		String message = "";
		int status = 0;
		if (service.create(route)) {
			status = HttpServletResponse.SC_OK;
			message = "New route added";
			LOG.info(String.format("New route added ==> %s", route.toString()));
		} else {
			status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			message = "Failed to create route";
			LOG.error(message);
		}

		res.setStatus(status);
		return message;
	}

}
