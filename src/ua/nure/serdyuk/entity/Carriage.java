package ua.nure.serdyuk.entity;

import java.math.BigDecimal;
import java.util.List;

public class Carriage {

	private long id;
	
	private int carTypeId;

	private CarriageType type;

	private String tag;

	private BigDecimal price;

	private List<Integer> seatsTaken;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
	public String toString() {
		return "Carriage [id=" + id + ", type=" + type + ", tag=" + tag
				+ ", price=" + price + ", seatsTaken=" + seatsTaken + "]";
	}

	public static class CarriageType {

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
					+ seatNum + "]";
		}
	}
}
