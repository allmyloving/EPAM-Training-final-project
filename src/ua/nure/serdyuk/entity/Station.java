package ua.nure.serdyuk.entity;

import java.io.Serializable;

public class Station implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Station [id=" + id + ", name=" + name + "]";
	}
}
