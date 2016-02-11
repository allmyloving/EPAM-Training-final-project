package ua.nure.serdyuk.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.CarriageService;
import ua.nure.serdyuk.entity.Carriage;
import ua.nure.serdyuk.entity.CarriageListBean;
import ua.nure.serdyuk.entity.TrainInfoBean;

public class GetFreeSeatsCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(GetFreeSeatsCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String param = req.getParameter("routeId");
		List<TrainInfoBean> trainInfoBeans = (List<TrainInfoBean>) req
				.getSession().getAttribute(Const.TRAIN_INFO_BEANS);
		TrainInfoBean temp = new TrainInfoBean();
		temp.setRouteId(Long.valueOf(param));

		LOG.debug("beans ==> " + trainInfoBeans);

		TrainInfoBean bean = trainInfoBeans.get(trainInfoBeans.indexOf(temp));

		CarriageService carriageService = (CarriageService) req
				.getServletContext().getAttribute(Const.CARRIAGE_SERVICE);
		List<Carriage> carriages = carriageService
				.getAllByTrainId(bean.getTrainId(), bean.getRouteId());

		LOG.debug("carriages found ==> " + carriages);

		req.setAttribute(Const.CARRIAGE_LIST, new CarriageListBean(carriages));

		return Path.FREE_SEATS_VIEW;
	}

}
