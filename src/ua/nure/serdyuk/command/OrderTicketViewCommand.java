package ua.nure.serdyuk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.entity.Carriage;
import ua.nure.serdyuk.entity.bean.CarriageListBean;
import ua.nure.serdyuk.entity.bean.TicketOrderBean;

public class OrderTicketViewCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(OrderTicketViewCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String carriageIdStr = req.getParameter("carriageId");
		String seatNumStr = req.getParameter("seatNum");

		long carriageId = Long.valueOf(carriageIdStr);
		int seatNum = Integer.valueOf(seatNumStr);
		long routeId = (long) req.getSession().getAttribute(Const.ROUTE_ID);
		CarriageListBean list = (CarriageListBean) req
				.getAttribute(Const.CARRIAGE_LIST);
		Carriage tmp = new Carriage();
		tmp.setId(carriageId);
		
		Carriage carriage = list.getCarriages().get(list.getCarriages().indexOf(tmp));

		LOG.info(String.format("carId=%d, seatNum=%d, routeId=%d", carriageId,
				seatNum, routeId));
		LOG.info(list);

		TicketOrderBean bean = new TicketOrderBean();
		bean.setSeatNum(seatNum);
		bean.setCarriage(carriage);
//		.bean.set

		return Path.ORDER_TICKET_VIEW;
	}

}
