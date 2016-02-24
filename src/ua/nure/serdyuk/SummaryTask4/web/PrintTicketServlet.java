package ua.nure.serdyuk.SummaryTask4.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.constants.Const;
import ua.nure.serdyuk.SummaryTask4.constants.Path;
import ua.nure.serdyuk.SummaryTask4.entity.User;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TicketOrderBean;
import ua.nure.serdyuk.SummaryTask4.exception.AppException;
import ua.nure.serdyuk.SummaryTask4.util.ETicket;
import ua.nure.serdyuk.SummaryTask4.util.MailSender;

@WebServlet("/print")
public class PrintTicketServlet extends HttpServlet {

	private static final long serialVersionUID = -7524283826508142297L;

	private static final Logger LOG = Logger
			.getLogger(PrintTicketServlet.class);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idStr = req.getParameter("id");
		HttpSession session = req.getSession();
		TicketOrderBean bean = null;

		if (idStr == null) {
			bean = (TicketOrderBean) session
					.getAttribute(Const.TICKET_ORDER_BEAN);
		} else {
			@SuppressWarnings("unchecked")
			List<TicketOrderBean> beans = (List<TicketOrderBean>) session
					.getAttribute(Const.TICKETS);
			bean = new TicketOrderBean();
			bean.setTicketId(Long.valueOf(idStr));
			int i = beans.indexOf(bean);
			if (i < 0) {
				throw new AppException("You can only print your own tickets.");
			}
			bean = beans.get(i);
		}
		LOG.info(String.format("generating ticket for %s", bean.toString()));

		String path = req.getServletContext()
				.getRealPath(System.getProperty("file.separator"));
		LOG.debug(path);

		String fileName = new StringBuilder(path).append(Path.ETICKET_NAME)
				.toString();
		ETicket eTicket = new ETicket(bean);
		ByteArrayOutputStream baos = eTicket.createDocument(fileName);

		sendTicket(session, fileName);

		resp.setContentType("application/pdf");
		resp.addHeader("Content-Disposition",
				String.format("attachment; filename=%s", Path.ETICKET_NAME));
		resp.setContentLength(baos.size());

		ServletOutputStream os = resp.getOutputStream();
		baos.writeTo(os);
		os.flush();
		os.close();

		baos.close();
	}

	private void sendTicket(HttpSession session, String fileName) {
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		try {
			new MailSender().sendMail(currentUser.getEmail(), fileName);
		} catch (MessagingException | IOException e) {
			LOG.error("Ticket was not send due to " + e);
		}
	}

}
