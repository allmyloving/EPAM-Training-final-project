package ua.nure.serdyuk.entity;

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
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public int getRouteId() {
		return trainId;
	}

	public void setRouteId(int routeId) {
		this.trainId = routeId;
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
