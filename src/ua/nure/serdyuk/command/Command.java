package ua.nure.serdyuk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class represents an interface for commands (Command design pattern) used
 * in this app.
 * 
 * @author Daria Serdiuk
 * @see CommandContainer
 */
public interface Command {

	/**
	 * Execution method for command.
	 * 
	 * @return address, where user should be redirected
	 */
	// throws AppException
	String execute(HttpServletRequest req, HttpServletResponse res);
}
