package ua.nure.serdyuk.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Path;

public class AddRouteViewCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(AddRouteViewCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		// get all trains
		return Path.ADD_ROUTE_VIEW;
	}

}
