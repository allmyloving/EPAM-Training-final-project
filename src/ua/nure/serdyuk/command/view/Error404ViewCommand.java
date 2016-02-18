package ua.nure.serdyuk.command.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Path;

public class Error404ViewCommand implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		return Path.ERROR_404_VIEW;
	}

}
