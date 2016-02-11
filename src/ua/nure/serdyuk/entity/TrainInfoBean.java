package ua.nure.serdyuk.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TrainInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long trainId;

	private long routeId;

	private String trainTag;

	private String stationFrom;

	private String stationTo;

	private Date depDate;

	private Date arrDate;

	private Date duration;

	List<RouteInfoBean> routes;

	List<Carriage> carriages;

	public String getTrainTag() {
		return trainTag;
	}

	public void setTrainTag(String trainTag) {
		this.trainTag = trainTag;
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

	public List<RouteInfoBean> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteInfoBean> routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "TrainInfoBean [trainId=" + trainId + ", trainTag=" + trainTag
				+ ", stationFrom=" + stationFrom + ", stationTo=" + stationTo
				+ ", depDate=" + depDate + ", arrDate=" + arrDate
				+ ", duration=" + duration + ", routes=" + routes
				+ ", carriages=" + carriages + "]";
	}

	@Override
	public boolean equals(Object obj) {
		TrainInfoBean other = (TrainInfoBean) obj;
		return this.routeId == other.routeId;
	}

	public static class RouteInfoBean implements Serializable {

		private static final long serialVersionUID = 7584579995712772109L;

		private String stationName;

		private Date arrTime;

		private Date depTime;

		public String getStationName() {
			return stationName;
		}

		public void setStationName(String stationName) {
			this.stationName = stationName;
		}

		public Date getDepTime() {
			return depTime;
		}

		public void setDepTime(Date depDate) {
			this.depTime = depDate;
		}

		public Date getArrTime() {
			return arrTime;
		}

		public void setArrTime(Date arrDate) {
			this.arrTime = arrDate;
		}

		@Override
		public String toString() {
			return "RouteInfoBean [stationName=" + stationName + ", arrTime="
					+ arrTime + ", depTime=" + depTime + "]";
		}
	}
}
