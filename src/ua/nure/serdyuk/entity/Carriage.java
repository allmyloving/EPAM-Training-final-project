package ua.nure.serdyuk.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carriage implements Serializable{

	private static final long serialVersionUID = -2429606477947350669L;

	private long id;

	private int carTypeId;

	private CarriageType type;

	private String tag;

	private List<Integer> seatsTaken;

	private Map<Integer, Boolean> seats;

	public Carriage() {
		seats = new HashMap<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(int carTypeId) {
		this.carTypeId = carTypeId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Map<Integer, Boolean> getSeats() {
		return seats;
	}

	public void setSeats(Map<Integer, Boolean> seats) {
		this.seats = seats;
	}

	public List<Integer> getSeatsTaken() {
		return seatsTaken;
	}

	public void setSeatsTaken(List<Integer> seatsTaken) {
		this.seatsTaken = seatsTaken;
	}

	public CarriageType getType() {
		return type;
	}

	public void setType(CarriageType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		Carriage other = (Carriage) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Carriage [id=" + id + ", type=" + type + ", tag=" + tag
				+ ", seatsTaken=" + seatsTaken + "]";
	}

	public static class CarriageType
			implements Serializable, Comparable<CarriageType> {

		private static final long serialVersionUID = 6944319122658319857L;

		private int id;

		private String name;

		private int seatNum;

		private BigDecimal price;

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

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		@Override
		public boolean equals(Object obj) {
			CarriageType other = (CarriageType) obj;
			return other.id == this.id;
		}

		@Override
		public String toString() {
			return "CarriageType [id=" + id + ", name=" + name + ", seatNum="
					+ seatNum + ", price=" + price + "]";
		}

		@Override
		public int compareTo(CarriageType o) {
			return o.getPrice().compareTo(getPrice());
		}
	}
}
