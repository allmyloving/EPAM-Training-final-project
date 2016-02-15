package ua.nure.serdyuk.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Train implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	
	private String tag;
	
	private BigDecimal price;
	
	private int typeId;
	
	private List<RouteItem> routeItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public List<RouteItem> getRouteItems() {
		return routeItems;
	}

	public void setRouteItems(List<RouteItem> routeItems) {
		this.routeItems = routeItems;
	}

	@Override
	public String toString() {
		return "Train [id=" + id + ", tag=" + tag + ", price=" + price
				+ ", typeId=" + typeId + "]";
	}
}
