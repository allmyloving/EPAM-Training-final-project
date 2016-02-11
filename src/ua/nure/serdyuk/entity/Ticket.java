package ua.nure.serdyuk.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private long userId;
	
	private long seatId;
	
	// or use enum
	private String status;
	
	private long discountTypeId;
	
	private Map<String, BigDecimal> facilities;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getSeatId() {
		return seatId;
	}

	public void setSeatId(long seatId) {
		this.seatId = seatId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getDiscountTypeId() {
		return discountTypeId;
	}

	public void setDiscountTypeId(long discountTypeId) {
		this.discountTypeId = discountTypeId;
	}

	public Map<String, BigDecimal> getFacilities() {
		return facilities;
	}

	public void setFacilities(Map<String, BigDecimal> facilities) {
		this.facilities = facilities;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", userId=" + userId + ", seatId=" + seatId
				+ ", status=" + status + ", discountTypeId=" + discountTypeId
				+ ", facilities=" + facilities + "]";
	}
}
