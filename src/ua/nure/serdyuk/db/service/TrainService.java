package ua.nure.serdyuk.db.service;

import java.util.List;

import ua.nure.serdyuk.entity.Train;

public interface TrainService {
	
	boolean create(Train train);

	boolean update(Train train);

	List<Train> getAll();
}
