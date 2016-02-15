package ua.nure.serdyuk.command.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.db.service.StationService;
import ua.nure.serdyuk.entity.Station;

public class GetStationsCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(GetStationsCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		StationService service = (StationService) req.getServletContext()
				.getAttribute(Const.STATION_SERVICE);
		List<Station> stations;
		String filter = req.getParameter("filter");

		LOG.trace("filter ==> " + filter);
		stations = service.getAll(filter);
		
		LOG.debug(String.format("Stations ==> %s", stations.toString()));

		String json = convertToJSON(stations);
		LOG.trace("JSON ==> " + json);

		return json;
	}
	
	@SuppressWarnings("unchecked")
	private String convertToJSON(List<Station> stations){
		JSONArray json = new JSONArray();
		JSONObject temp;
		for (Station item : stations) {
			temp = new JSONObject();
			temp.put("name", item.getName());
			json.add(temp);
		}
		JSONObject obj = new JSONObject();
		obj.put("stations", json);
		
		return obj.toJSONString();
	}

}
