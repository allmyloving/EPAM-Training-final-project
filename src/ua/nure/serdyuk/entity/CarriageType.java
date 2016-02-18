package ua.nure.serdyuk.entity;

import java.io.Serializable;

public class CarriageType implements Serializable, Comparable<CarriageType> {

	private static final long serialVersionUID = 6944319122658319857L;

	private int id;

	private String name;

	private int seatNum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	@Override
	public boolean equals(Object obj) {
		CarriageType other = (CarriageType) obj;
		return other.id == this.id;
	}

	@Override
	public String toString() {
		return "CarriageType [id=" + id + ", name=" + name + ", seatNum="
				+ seatNum + "]";
	}

	@Override
	public int compareTo(CarriageType o) {
		return o.name.compareTo(name);
	}
}
