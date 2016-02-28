package ua.nure.serdyuk.SummaryTask4.util.graph;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Graph {

	private static final Logger LOG = Logger.getLogger(Graph.class);

	List<Verticle> verticles;

	public Graph() {
		verticles = new ArrayList<>();
	}

	public Verticle get(int stationId) {
		Verticle v = new Verticle(stationId);
		int index = verticles.indexOf(v);

		if (index >= 0) {
			return verticles.get(index);
		}
		verticles.add(v);
		return v;
	}

	public void findPath(int from, int to) {
		for (Edge e : get(from).getEdges()) {
			int next;
			// do {
			// next = e.getVerticle().getStationId();
			// } while (next != to);

			LOG.info(get(new ArrayList<Verticle>(), get(from), e.getTrainId(),
					to));
		}
	}

	private List<Verticle> get(List<Verticle> verticles, Verticle current,
			int trainId, int to) {
		//LOG.debug("current ==> " + current);
		if (verticles == null) {
			return verticles;
		}
		if (verticles.isEmpty()) {
			verticles.add(current);
		}
		if (current.getStationId() == to) {
			return verticles;
		}
		for (Edge e : current.getEdges()) {
			LOG.debug(e);
//			LOG.debug(trainId);
			if (e.getTrainId() == trainId) {
				LOG.debug(String.format("Adding %d from %s",
						e.getVerticle().getStationId(), e.toString()));
				verticles.add(e.getVerticle());
				return get(verticles, e.getVerticle(), trainId, to);
			}
			//return get(verticles, e.getVerticle(), e.getTrainId(), to);
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		final String separ = System.lineSeparator();
		final char tab = '\t';
		sb.append("[Graph]").append(separ);

		for (Verticle v : verticles) {
			sb.append(tab).append(v.getStationId()).append(" ==> ")
					.append(v.getEdges()).append(separ);
		}

		return sb.toString();
	}

}
