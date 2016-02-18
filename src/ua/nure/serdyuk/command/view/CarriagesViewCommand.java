package ua.nure.serdyuk.command.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.CarriageTypeService;
import ua.nure.serdyuk.entity.CarriageType;

public class CarriagesViewCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(CarriagesViewCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String trainId = req.getParameter("trainId");
		if (trainId != null) {
			int id = Integer.valueOf(trainId);
			req.getSession().setAttribute(Const.TRAIN_ID, id);
		}

		CarriageTypeService service = (CarriageTypeService) req
				.getServletContext().getAttribute(Const.CARRIAGE_TYPE_SERVICE);
		List<CarriageType> types = service.getAll();
		LOG.info(String.format("Carriage types obtained ==> %s", types));

		req.setAttribute("carTypes", types);

		return Path.CARRIAGES_VIEW;
	}

}
