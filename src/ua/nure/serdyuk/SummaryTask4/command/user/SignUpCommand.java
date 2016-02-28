package ua.nure.serdyuk.SummaryTask4.command.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.command.Command;
import ua.nure.serdyuk.SummaryTask4.constants.Const;
import ua.nure.serdyuk.SummaryTask4.constants.Path;
import ua.nure.serdyuk.SummaryTask4.entity.Role;
import ua.nure.serdyuk.SummaryTask4.entity.User;
import ua.nure.serdyuk.SummaryTask4.util.DateUtils;
import ua.nure.serdyuk.SummaryTask4.util.MailSender;
import ua.nure.serdyuk.SummaryTask4.util.Util;
import ua.nure.serdyuk.SummaryTask4.validation.Validator;

public class SignUpCommand implements Command {

	private static final Logger LOG = Logger.getLogger(SignUpCommand.class);

	private static final String PASSWORD_REPEAT = "repPassword";

	private static final String FIRST_NAME = "firstName";

	private static final String LAST_NAME = "lastName";

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String email = req.getParameter(Const.EMAIL);
		String password = req.getParameter(Const.PASSWORD);
		String repPassword = req.getParameter(PASSWORD_REPEAT);
		String firstName = req.getParameter(FIRST_NAME);
		String lastName = req.getParameter(LAST_NAME);

		HttpSession session = req.getSession();
		session.setAttribute(Const.EMAIL, email);
		session.setAttribute(FIRST_NAME, firstName);
		session.setAttribute(LAST_NAME, lastName);

		List<String> errors = new ArrayList<>();
		session.setAttribute(Const.ERRORS, errors);

		// validate
		if (repPassword == null || !repPassword.equals(password)) {
			errors.add("message.passwords_not_equal");
			// throw new ValidationException();
		}

		User user = validate(email, repPassword, firstName, lastName, errors);
		if (!errors.isEmpty()) {

			return Path.SIGN_UP_VIEW_COMMAND;
			// throw new ValidationException();
		}
		String key = generateKey(user);
		session.setAttribute(Const.KEY, key);
		session.setAttribute(Const.USER_BEAN, user);

		new MailSender().sendConfirmation(user.getEmail(),
				generateLink(req, key));
		session.setAttribute("loginError", "message.confirm_email");

		return Path.LOGIN_VIEW_COMMAND;
	}

	private String generateKey(User user) {
		String input = String.format("%s%s", DateUtils.today().toString(),
				user.getEmail());
		return Util.hash(input);
	}

	private String generateLink(HttpServletRequest req, String key) {
		String url = String.format("%s?command=signUpConfirm&key=%s",
				req.getRequestURL().toString(), key);
		LOG.debug("url ==> " + url);

		return url;
	}

	private User validate(String email, String password, String firstName,
			String lastName, List<String> errors) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setRole(Role.USER);

		errors.addAll(Validator.validate(user));
		if (!errors.isEmpty()) {
			LOG.error(String.format("Validation failed -- %s",
					errors.toString()));
		}

		return user;
	}

}
