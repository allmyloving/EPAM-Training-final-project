package ua.nure.serdyuk.command.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.TrainBeanService;
import ua.nure.serdyuk.entity.bean.TrainBean;

public class RouteViewCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(RouteViewCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		TrainBeanService service = (TrainBeanService) req.getServletContext()
				.getAttribute(Const.TRAIN_BEAN_SERVICE);
		List<TrainBean> beans = service.getAll();

		LOG.info(String.format("Trains found ==> %s", beans));
		
		HttpSession session = req.getSession();
		session.setAttribute("trainBeans", beans);

		return Path.ROUTE_VIEW;
	}

}
