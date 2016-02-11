package ua.nure.serdyuk.entity;

import java.math.BigDecimal;
import java.util.List;

public class Carriage {
	
	private long id;
	
	private String type;
	
	private String tag;
	
	private BigDecimal price;
	
	private int seatNum;
	
	private List<Integer> seatsTaken;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public List<Integer> getSeatsTaken() {
		return seatsTaken;
	}

	public void setSeatsTaken(List<Integer> seatsTaken) {
		this.seatsTaken = seatsTaken;
	}

	@Override
	public String toString() {
		return "Carriage [type=" + type + ", tag=" + tag + ", seatNum="
				+ seatNum + ", seatsTaken=" + seatsTaken + "]";
	}
}
