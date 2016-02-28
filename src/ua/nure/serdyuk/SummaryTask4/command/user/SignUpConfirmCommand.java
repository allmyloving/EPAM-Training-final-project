package ua.nure.serdyuk.SummaryTask4.command.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.command.Command;
import ua.nure.serdyuk.SummaryTask4.constants.Const;
import ua.nure.serdyuk.SummaryTask4.constants.Path;
import ua.nure.serdyuk.SummaryTask4.db.service.UserService;
import ua.nure.serdyuk.SummaryTask4.entity.User;
import ua.nure.serdyuk.SummaryTask4.exception.AppException;

public class SignUpConfirmCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(SignUpConfirmCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String key = req.getParameter(Const.KEY);
		String keySession = (String) req.getSession().getAttribute(Const.KEY);
		HttpSession session = req.getSession();

		if (key.equals(keySession)) {
			User user = (User) session.getAttribute(Const.USER_BEAN);
			UserService service = (UserService) req.getServletContext()
					.getAttribute(Const.USER_SERVICE);
			
			if (service.create(user)) {
				LOG.info("User was successfully created");
			} else {
				LOG.error("User creation failed");
				throw new AppException("User creation failed");
			}
		}

		session.setAttribute("loginError", "message.confirmed");
		return Path.LOGIN_VIEW_COMMAND;
	}

}
