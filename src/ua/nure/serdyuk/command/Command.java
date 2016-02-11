package ua.nure.serdyuk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	
	// throws AppException
	String execute(HttpServletRequest req, HttpServletResponse res);
}
