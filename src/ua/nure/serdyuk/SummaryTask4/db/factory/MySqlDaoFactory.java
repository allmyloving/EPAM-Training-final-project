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
import ua.nure.serdyuk.SummaryTask4.db.dao.mysql.CarriageDaoMySql;
import ua.nure.serdyuk.SummaryTask4.db.dao.mysql.CarriageTypeDaoMySql;
import ua.nure.serdyuk.SummaryTask4.db.dao.mysql.RouteBeanDaoMySql;
import ua.nure.serdyuk.SummaryTask4.db.dao.mysql.RouteDaoMySql;
import ua.nure.serdyuk.SummaryTask4.db.dao.mysql.RouteItemDaoMySql;
import ua.nure.serdyuk.SummaryTask4.db.dao.mysql.StationDaoMySql;
import ua.nure.serdyuk.SummaryTask4.db.dao.mysql.TicketDaoMySql;
import ua.nure.serdyuk.SummaryTask4.db.dao.mysql.TrainBeanDaoMySql;
import ua.nure.serdyuk.SummaryTask4.db.dao.mysql.TrainDaoMySql;
import ua.nure.serdyuk.SummaryTask4.db.dao.mysql.UserDaoMySql;

public class MySqlDaoFactory implements DaoFactory {

	@Override
	public CarriageDao getCarriageDao() {
		return new CarriageDaoMySql();
	}

	@Override
	public CarriageTypeDao getCarriageTypeDao() {
		return new CarriageTypeDaoMySql();
	}

	@Override
	public RouteBeanDao getRouteBeanDao() {
		return new RouteBeanDaoMySql();
	}

	@Override
	public RouteDao getRouteDao() {
		return new RouteDaoMySql();
	}

	@Override
	public RouteItemDao getRouteItemDao() {
		return new RouteItemDaoMySql();
	}

	@Override
	public StationDao getStationDao() {
		return new StationDaoMySql();
	}

	@Override
	public TicketDao getTicketDao() {
		return new TicketDaoMySql();
	}

	@Override
	public TrainBeanDao getTrainBeanDao() {
		return new TrainBeanDaoMySql();
	}

	@Override
	public TrainDao getTrainDao() {
		return new TrainDaoMySql();
	}

	@Override
	public UserDao getUserDao() {
		return new UserDaoMySql();
	}
}
