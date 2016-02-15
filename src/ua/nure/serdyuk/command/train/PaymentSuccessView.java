package ua.nure.serdyuk.command.train;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Path;

public class PaymentSuccessView implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		return Path.PAYMENT_SUCCESSFUL_VIEW;
	}

}
