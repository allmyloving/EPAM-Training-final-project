package ua.nure.serdyuk.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.command.CommandContainer;

@WebServlet("/serv")
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(MyServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// what if wrong command?
		String commandParam = req.getParameter("command");
		Command command = CommandContainer.get(commandParam);

		String out = command.execute(req, resp);

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print(out);
		writer.flush();
	}
}
