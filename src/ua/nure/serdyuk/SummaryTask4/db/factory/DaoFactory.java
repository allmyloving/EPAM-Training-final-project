package ua.nure.serdyuk.SummaryTask4.db.factory;

import ua.nure.serdyuk.SummaryTask4.db.dao.CarriageDao;
import ua.nure.serdyuk.SummaryTask4.db.dao.CarriageTypeDao;
import ua.nure.serdyuk.SummaryTask4.db.dao.RouteBeanDao;
import ua.nure.serdyuk.SummaryTask4.db.dao.RouteDao;
import ua.nure.serdyuk.SummaryTask4.db.dao.RouteItemDao;
import ua.nure.serdyuk.SummaryTask4.db.dao.StationDao;
import ua.nure.serdyuk.SummaryTask4.db.dao.TicketDao;
import ua.nure.serdyuk.SummaryTask4.db.dao.TrainBeanDao;
import ua.nure.serdyuk.SummaryTask4.db.dao.TrainDao;
import ua.nure.serdyuk.SummaryTask4.db.dao.UserDao;

public interface DaoFactory {

	CarriageDao getCarriageDao();

	CarriageTypeDao getCarriageTypeDao();

	RouteBeanDao getRouteBeanDao();

	RouteDao getRouteDao();

	RouteItemDao getRouteItemDao();

	StationDao getStationDao();

	TicketDao getTicketDao();

	TrainBeanDao getTrainBeanDao();

	TrainDao getTrainDao();

	UserDao getUserDao();

}
