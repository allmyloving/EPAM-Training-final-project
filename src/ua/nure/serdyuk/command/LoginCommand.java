package ua.nure.serdyuk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.Message;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.UserService;
import ua.nure.serdyuk.entity.User;

public class LoginCommand implements Command {

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		UserService service = (UserService) req.getServletContext()
				.getAttribute(Const.USER_SERVICE);
		String email = req.getParameter(Const.EMAIL);
		String password = req.getParameter(Const.PASSWORD);

		User user = service.auth(email, password);
		if (user != null) {
			req.getSession().setAttribute(Const.CURRENT_USER, user);
			LOG.info("Login successful");
			return Path.INDEX_VIEW;
		}

		LOG.info(String.format("Login failed for email %s", email));

		req.setAttribute(Const.EMAIL, email);
		req.setAttribute("loginError", Message.INVALID_USER_NAME_OR_PASSWORD);
		
		return Path.LOGIN_VIEW;
	}

}
