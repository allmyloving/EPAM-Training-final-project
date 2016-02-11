package ua.nure.serdyuk.db.service;

import java.util.List;

import ua.nure.serdyuk.entity.Carriage;

public interface CarriageService {

	List<Carriage> getAllByTrainId(long trainId, long routeId);
}
