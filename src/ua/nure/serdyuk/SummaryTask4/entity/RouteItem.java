package ua.nure.serdyuk.SummaryTask4.entity;

import java.io.Serializable;
import java.util.Date;

public class RouteItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private Date arrivalTime;

	private Date departureTime;

	private int ordinal;

	private int trainId;

	private int stationId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getArrivalTime() {
		return arrivalTime != null ? (Date) arrivalTime.clone() : null;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime != null ? (Date) arrivalTime.clone()
				: arrivalTime;
	}

	public Date getDepartureTime() {
		return departureTime != null ? (Date) departureTime.clone() : null;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime != null
				? (Date) departureTime.clone() : departureTime;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	@Override
	public String toString() {
		return "RouteItem [id=" + id + ", arrivalTime=" + arrivalTime
				+ ", departureTime=" + departureTime + ", ordinal=" + ordinal
				+ ", routeId=" + trainId + ", stationId=" + stationId + "]";
	}
}
