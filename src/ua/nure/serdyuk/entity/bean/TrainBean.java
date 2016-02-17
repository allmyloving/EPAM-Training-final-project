package ua.nure.serdyuk.entity.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import ua.nure.serdyuk.entity.Carriage;

public class TrainBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long trainId;

	private long routeId;

	private long routeItemIdFrom;

	private long routeItemIdTo;

	private String trainTag;

	private String stationFrom;

	private String stationTo;

	private Date depDate;

	private Date arrDate;

	private Date duration;

	List<RouteBean> routes;

	List<Carriage> carriages;

	public String getTrainTag() {
		return trainTag;
	}

	public void setTrainTag(String trainTag) {
		this.trainTag = trainTag;
	}

	public long getRouteItemIdFrom() {
		return routeItemIdFrom;
	}

	public void setRouteItemIdFrom(long routeItemIdFrom) {
		this.routeItemIdFrom = routeItemIdFrom;
	}

	public long getRouteItemIdTo() {
		return routeItemIdTo;
	}

	public void setRouteItemIdTo(long routeItemIdTo) {
		this.routeItemIdTo = routeItemIdTo;
	}

	public String getStationFrom() {
		return stationFrom;
	}

	public void setStationFrom(String stationFrom) {
		this.stationFrom = stationFrom;
	}

	public String getStationTo() {
		return stationTo;
	}

	public void setStationTo(String stationTo) {
		this.stationTo = stationTo;
	}

	public Date getDepDate() {
		return depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	public Date getArrDate() {
		return arrDate;
	}

	public void setArrDate(Date arrDate) {
		this.arrDate = arrDate;
	}

	public Date getDuration() {
		return duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public long getTrainId() {
		return trainId;
	}

	public void setTrainId(long trainId) {
		this.trainId = trainId;
	}

	public long getRouteId() {
		return routeId;
	}

	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	public List<Carriage> getCarriages() {
		return carriages;
	}

	public void setCarriages(List<Carriage> carriages) {
		this.carriages = carriages;
	}

	public List<RouteBean> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteBean> routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "TrainBean [trainId=" + trainId + ", routeId=" + routeId
				+ ", routeItemIdFrom=" + routeItemIdFrom + ", routeItemIdTo="
				+ routeItemIdTo + ", trainTag=" + trainTag + ", stationFrom="
				+ stationFrom + ", stationTo=" + stationTo + ", depDate="
				+ depDate + ", arrDate=" + arrDate + ", duration=" + duration
				+ ", routes=" + routes + ", carriages=" + carriages + "]";
	}

	@Override
	public boolean equals(Object obj) {
		TrainBean other = (TrainBean) obj;
		if (other.routeId != 0 && this.routeId != 0) {
			return this.routeId == other.routeId;
		}
		return this.trainId == other.trainId;
	}
}
