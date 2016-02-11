package ua.nure.serdyuk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.serdyuk.constants.Path;

/**
 * This command is called when the requested command is not found. Therefore it
 * redirects user to error page.
 * 
 * @author Daria Serdiuk
 * @see Command
 */
public class NoCommand implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		return Path.ERROR_404_VIEW;
	}
}
