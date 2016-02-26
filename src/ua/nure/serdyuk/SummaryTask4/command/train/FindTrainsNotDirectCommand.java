package ua.nure.serdyuk.SummaryTask4.command.train;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.command.Command;
import ua.nure.serdyuk.SummaryTask4.constants.Const;
import ua.nure.serdyuk.SummaryTask4.constants.Path;
import ua.nure.serdyuk.SummaryTask4.db.service.RouteItemService;
import ua.nure.serdyuk.SummaryTask4.entity.RouteItem;
import ua.nure.serdyuk.SummaryTask4.util.graph.Edge;
import ua.nure.serdyuk.SummaryTask4.util.graph.Graph;

public class FindTrainsNotDirectCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(FindTrainsNotDirectCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String from = req.getParameter("f");
		String to = req.getParameter("t");
		
		RouteItemService routeItemService = (RouteItemService) req
				.getServletContext().getAttribute(Const.ROUTE_ITEM_SERVICE);
		List<RouteItem> routeItems = routeItemService.getAll();

		Graph graph = new Graph();
		RouteItem ri, next;

		for (int i = 0; i < routeItems.size(); i++) {
			ri = routeItems.get(i);
			if (ri.getDepartureTime() == null) {
				continue;
			}
			next = routeItems.get(i + 1);
			graph.get(ri.getStationId()).addEdge(
					new Edge(ri.getTrainId(), graph.get(next.getStationId())));
		}
		LOG.info(graph);
		
		int f = Integer.valueOf(from);
		int t = Integer.valueOf(to);
		graph.findPath(f, t);

		return Path.INDEX_VIEW;
	}

}
