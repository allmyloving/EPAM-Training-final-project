package ua.nure.serdyuk.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.db.dao.mysql.RouteDaoMySql;
import ua.nure.serdyuk.db.service.RouteService;
import ua.nure.serdyuk.db.service.StationService;
import ua.nure.serdyuk.db.service.impl.RouteServiceMySql;
import ua.nure.serdyuk.entity.Station;

@WebServlet("/serv")
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(MyServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String param = req.getParameter("param");
		LOG.debug("param ==> " + param);

		String out = "";
		if ("errors".equals(param)) {
			Object attr = req.getAttribute(param);
			LOG.trace("Attr ==> " + attr);
			out = String.valueOf(attr);
		}
		if ("stations".equals(param)) {
			StationService service = (StationService) req.getServletContext()
					.getAttribute(Const.STATION_SERVICE);
			List<Station> stations;
			String filter = req.getParameter("filter");

			// change logic here
			if (filter != null) {
				//filter = new String(filter.getBytes("iso-8859-1"), "utf-8");
				LOG.trace("filter ==> " + filter);
				// out = service.getAll(filter).toString();
				stations = service.getAll(filter);
			} else {
				stations = service.getAll();
			}

			JSONArray json = new JSONArray();
			JSONObject temp;
			for (Station item : stations) {
				temp = new JSONObject();
				temp.put("name", item.getName());
				json.add(temp);
			}
			JSONObject obj = new JSONObject();
			obj.put("stations", json);
			LOG.trace(String.format("Stations ==> %s", out));

			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.print(obj.toJSONString());
			writer.flush();

			LOG.trace("JSON ==> " + obj.toJSONString());
		}
		
		// resource not closed
		// resp.setContentType("text/html; charset=UTF-8");
		// PrintWriter writer = resp.getWriter();
		// writer.print(out);
		// writer.flush();
	}
}
