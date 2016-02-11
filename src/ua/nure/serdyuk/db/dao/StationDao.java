package ua.nure.serdyuk.db.dao;

import java.util.List;

import ua.nure.serdyuk.entity.Station;

public interface StationDao extends GenericDao<Station> {
	
	List<Station> getAll(String filter);
	
	Station getByName(String name);
}
