package ua.nure.serdyuk.SummaryTask4.util.graph;

import java.util.ArrayList;
import java.util.List;

public class Verticle {

	private final int stationId;

	private List<Edge> edges;

	private boolean visited;

	public Verticle(int stationId) {
		this.stationId = stationId;
		edges = new ArrayList<>();
	}

	public int getStationId() {
		return stationId;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}

	public boolean isVisited() {
		return visited;
	}

	public void visit() {
		visited = true;
	}

	@Override
	public String toString() {
		return "V [id=" + stationId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stationId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Verticle other = (Verticle) obj;
		if (stationId != other.stationId) {
			return false;
		}
		return true;
	}
}
