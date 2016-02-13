package ua.nure.serdyuk.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.StationService;
import ua.nure.serdyuk.entity.Carriage;
import ua.nure.serdyuk.entity.Station;
import ua.nure.serdyuk.entity.bean.TicketOrderBean;
import ua.nure.serdyuk.entity.bean.TrainBean;

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
		// CarriageListBean list = (CarriageListBean) req
		// .getAttribute(Const.CARRIAGE_LIST);

		List<TrainBean> trainBeans = (List<TrainBean>) req.getSession()
				.getAttribute(Const.TRAIN_INFO_BEANS);
		TrainBean temp = new TrainBean();
		temp.setRouteId(routeId);
		TrainBean trainBean = trainBeans.get(trainBeans.indexOf(temp));

		LOG.info("Train bean ==> " + trainBean);

		Carriage tmp = new Carriage();
		tmp.setId(carriageId);
		Carriage carriage = trainBean.getCarriages()
				.get(trainBean.getCarriages().indexOf(tmp));

		LOG.info("Carriage ==> " + carriage);

		StationService stationService = (StationService) req.getServletContext()
				.getAttribute(Const.STATION_SERVICE);
		List<Station> stations = stationService.getByRouteItems(
				trainBean.getRouteItemIdFrom(), trainBean.getRouteItemIdTo());

		TicketOrderBean ticketBean = new TicketOrderBean();
		ticketBean.setSeatNum(seatNum);
		ticketBean.setCarriage(carriage);
		ticketBean.setTrainBean(trainBean);
		ticketBean.setStationFrom(stations.get(0).getName());
		ticketBean.setStationTo(stations.get(1).getName());
		// .bean.set

		req.getSession().setAttribute(Const.TICKET_ORDER_BEAN, ticketBean);

		return Path.ORDER_TICKET_VIEW;
	}

}
