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

	private BigDecimal price;

	private String tag;
	
	private int trainId;

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

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
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
}
