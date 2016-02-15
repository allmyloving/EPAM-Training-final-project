package ua.nure.serdyuk.command.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Message;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.StationService;
import ua.nure.serdyuk.exception.DbException;

public class DeleteStationCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(DeleteStationCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String stationIdStr = req.getParameter("stationId");
		long stationId = Long.valueOf(stationIdStr);

		StationService service = (StationService) req.getServletContext()
				.getAttribute(Const.STATION_SERVICE);

		List<String> errors = new ArrayList<>();
		boolean result = false;
		try {
			result = service.delete(stationId);
			
			if (!result) {
				errors.add(Message.SERVER_ERROR);
				LOG.error(String.format("Error while deleting station id=%d",
						stationId));
			}
		} catch (DbException e) {
			errors.add(Message.SERVER_ERROR);
			LOG.error(String.format("DbException while deleting station id=%d",
					stationId));
		}
		req.getSession().setAttribute(Const.ERRORS, errors);

		return Path.STATIONS_VIEW_COMMAND;
	}

}
