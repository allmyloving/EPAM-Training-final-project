package ua.nure.serdyuk.SummaryTask4.util.graph;

public class Edge {
	
	private final int trainId;
	
	private final Verticle verticle;

	public Edge(int trainId, Verticle verticle) {
		this.trainId = trainId;
		this.verticle = verticle;
	}

	public int getTrainId() {
		return trainId;
	}

	public Verticle getVerticle() {
		return verticle;
	}

	@Override
	public String toString() {
		return "E [w=" + trainId + ", "+ verticle + "]";
	}

}
