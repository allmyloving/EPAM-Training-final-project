package ua.nure.serdyuk.SummaryTask4.entity.bean;

import java.io.Serializable;

import ua.nure.serdyuk.SummaryTask4.entity.Carriage;

public class TicketOrderBean implements Serializable {

	private static final long serialVersionUID = -592514764415037535L;

	private String firstName;

	private String lastName;

	private String stationFrom;

	private String stationTo;

	private TrainBean trainBean;

	private int seatNum;

	private Carriage carriage;

	private long ticketId;

	public TrainBean getTrainBean() {
		return trainBean;
	}

	public void setTrainBean(TrainBean trainBean) {
		this.trainBean = trainBean;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
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

	public Carriage getCarriage() {
		return carriage;
	}

	public void setCarriage(Carriage carriage) {
		this.carriage = carriage;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ticketId ^ (ticketId >>> 32));
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
		TicketOrderBean other = (TicketOrderBean) obj;
		if (ticketId != other.ticketId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TicketOrderBean [firstName=" + firstName + ", lastName="
				+ lastName + ", trainBean=" + trainBean + ", seatNum=" + seatNum
				+ ", carriage=" + carriage + "]";
	}

}
