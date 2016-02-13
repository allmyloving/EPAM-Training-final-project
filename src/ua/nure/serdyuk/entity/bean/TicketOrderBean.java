package ua.nure.serdyuk.entity.bean;

import java.io.Serializable;

import ua.nure.serdyuk.entity.Carriage;

public class TicketOrderBean implements Serializable{

	private static final long serialVersionUID = -592514764415037535L;

	private String firstName;
	
	private String lastName;
	
	private long routeItemIdFrom;
	
	private long routeItemIdTo;
	
	private int seatNum;
	
	private Carriage carriage;

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

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public Carriage getCarriage() {
		return carriage;
	}

	public void setCarriage(Carriage carriage) {
		this.carriage = carriage;
	}

	@Override
	public String toString() {
		return "TicketOrderBean [firstName=" + firstName + ", lastName="
				+ lastName + ", routeItemIdFrom=" + routeItemIdFrom
				+ ", routeItemIdTo=" + routeItemIdTo + ", seatNum=" + seatNum
				+ ", carriage=" + carriage + "]";
	}
}
