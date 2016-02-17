package ua.nure.serdyuk.command.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.StationService;
import ua.nure.serdyuk.db.service.TrainBeanService;
import ua.nure.serdyuk.entity.Station;
import ua.nure.serdyuk.entity.bean.TrainBean;

public class AddTrainViewCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(AddTrainViewCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		StationService stationService = (StationService) req.getServletContext()
				.getAttribute(Const.STATION_SERVICE);

		List<Station> stations = stationService.getAll();
		LOG.info(
				String.format("Stations obtained ==> %s", stations.toString()));

		req.setAttribute("stations", stations);

		TrainBeanService service = (TrainBeanService) req.getServletContext()
				.getAttribute(Const.TRAIN_BEAN_SERVICE);
		List<TrainBean> beans = service.getAll();

		LOG.info(String.format("Trains found ==> %s", beans));

		req.setAttribute("trainBeans", beans);
		
		return Path.ADD_TRAIN_VIEW;
	}

}
