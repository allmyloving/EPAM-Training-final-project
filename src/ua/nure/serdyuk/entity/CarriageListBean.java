package ua.nure.serdyuk.entity;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CarriageListBean {

	private List<Carriage> carriages;

	public CarriageListBean(List<Carriage> carriages) {
		this.carriages = carriages;
	}

	public Set<String> getTypes() {
		Set<String> types = new TreeSet<>();
		for (Carriage c : carriages) {
			types.add(c.getType());
		}
		return types;
	}

	public List<Carriage> getCarriages() {
		return carriages;
	}

	@Override
	public String toString() {
		return "CarriageListBean [carriages=" + carriages + "]";
	}
}
