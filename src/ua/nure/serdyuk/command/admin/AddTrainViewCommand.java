package ua.nure.serdyuk.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.StationService;
import ua.nure.serdyuk.entity.Station;

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
		return Path.ADD_TRAIN_VIEW;
	}

}
