package ua.nure.serdyuk.SummaryTask4.util;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.db.service.TicketService;
import ua.nure.serdyuk.SummaryTask4.db.service.UserService;
import ua.nure.serdyuk.SummaryTask4.entity.User;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TicketOrderBean;

public class NotifyThread extends Thread {

	private static final Logger LOG = Logger.getLogger(NotifyThread.class);

	private static final long INTERVAL_DAY = 24 * 60 * 60 * 1000;

	private static final long INTERVAL_10_MIN = 60 * 60 * 1000;

	private TicketService ticketService;

	private UserService userService;

	private MailSender mailSender;

	public NotifyThread(TicketService ticketService, UserService userService) {
		this.ticketService = ticketService;
		this.userService = userService;
		this.mailSender = new MailSender();
	}

	@Override
	public void run() {
		while (true) {
			List<TicketOrderBean> tickets = ticketService.getAllForTomorrow();
			LOG.info(String.format("%s Tickets for tomorrow ==> %s",
					Thread.currentThread().getName(), tickets.toString()));

			for (TicketOrderBean t : tickets) {
				User u = userService.get(t.getUserId());

				if (mailSender.sendMail(u.getEmail())) {
					boolean result = ticketService.setNotified(t.getTicketId());
					LOG.debug(String.format("Ticket notified: %b", result));
				}
			}
			try {
				Thread.sleep(INTERVAL_10_MIN);
			} catch (InterruptedException e) {
				LOG.error("Thread was interrupted");
				this.interrupt();
			}
		}
	}

}
