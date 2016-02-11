package ua.nure.serdyuk.db.dao.mysql;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.PropertyContainer;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.db.DbUtils;
import ua.nure.serdyuk.db.dao.TrainInfoDao;
import ua.nure.serdyuk.entity.TrainInfoBean;
import ua.nure.serdyuk.entity.TrainInfoBean.RouteInfoBean;
import ua.nure.serdyuk.exception.DbException;

public class TrainInfoDaoMySql implements TrainInfoDao {

	private static final Logger LOG = Logger.getLogger(TrainInfoDaoMySql.class);

	@Override
	public boolean create(TrainInfoBean item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public TrainInfoBean get(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean update(TrainInfoBean item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(TrainInfoBean item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<TrainInfoBean> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TrainInfoBean getFullInfo(long trainId, long stFromId, long stToId,
			long routeId) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		TrainInfoBean bean = null;

		try {
			ps = conn.prepareStatement(PropertyContainer
					.get(Const.GET_TRAIN_INFO_BY_TRAIN_ID_AND_ROUTE));
			int k = 1;
			ps.setLong(k++, trainId);
			ps.setLong(k++, trainId);
			ps.setLong(k++, routeId);

			rs = ps.executeQuery();

			bean = new TrainInfoBean();

			java.sql.Date depDate = null;
			// throw ex?
			if (rs.next()) {
				depDate = rs.getDate("date");
				bean.setTrainTag(rs.getString("tag"));
				bean.setStationFrom(rs.getString("name"));

				if (rs.next()) {
					bean.setStationTo(rs.getString("name"));
				}
			}
			LOG.debug(String.format("Bean after 1st query ==> %s",
					bean.toString()));

			// not sure about those
			ps.close();
			rs.close();

			ps = conn.prepareStatement(PropertyContainer
					.get(Const.GET_ARR_DEP_TIME_BY_TRAIN_ID_AND_STATIONS));
			k = 1;
			ps.setLong(k++, trainId);
			ps.setLong(k++, stFromId);
			ps.setLong(k++, stToId);

			rs = ps.executeQuery();

			if (rs.next()) {
				bean.setDepDate(depDate);
				Time depTime = rs.getTime("dep_time");
				LOG.debug(String.format(
						"departure time as time=%s, as date=%s, as timestamp=%s",
						depTime, rs.getDate("dep_time"),
						rs.getTimestamp("dep_time")));
				LOG.debug("departure date is " + getDate(depDate, depTime));

				bean.setDepDate(getDate(depDate, depTime));
				bean.setArrDate(
						new java.util.Date(rs.getTime("arr_time").getTime()));
			}

			LOG.debug(String.format("Bean after 2nd query ==> %s",
					bean.toString()));

		} catch (SQLException e) {
			DbUtils.rollback(conn);
			throw new DbException(e.getMessage());
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		bean.setRoutes(getRouteInfo(trainId));

		return bean;
	}

	@Override
	public BigDecimal getPrice(long trainId, int carTypeId, int ordinalFrom,
			int ordinalTo) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		BigDecimal price = null;

		try {
			ps = conn.prepareStatement(PropertyContainer
					.get(Const.GET_PRICE_BY_TRAIN_ID_CAR_TYPE_AND_ORDINALS));
			int k = 1;
			ps.setLong(k++, trainId);
			ps.setInt(k++, carTypeId);
			ps.setLong(k++, trainId);
			ps.setInt(k++, ordinalFrom);
			ps.setInt(k++, ordinalTo);
			ps.setLong(k++, trainId);

			rs = ps.executeQuery();

			if (rs.next()) {
				price = rs.getBigDecimal(1);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage());
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		return price;
	}

	private java.util.Date getDate(java.sql.Date date, Time time) {
		java.util.Date d = new java.util.Date(date.getTime());
		java.util.Date t = new java.util.Date(time.getTime());

		Calendar dCal = Calendar.getInstance();
		dCal.setTime(d);

		Calendar tCal = Calendar.getInstance();
		tCal.setTime(t);

		dCal.set(Calendar.HOUR_OF_DAY, tCal.get(Calendar.HOUR_OF_DAY));
		dCal.set(Calendar.MINUTE, tCal.get(Calendar.MINUTE));
		dCal.set(Calendar.SECOND, tCal.get(Calendar.SECOND));

		// return new java.util.Date(date.getTime() + time.getTime());
		return dCal.getTime();
	}

	private List<RouteInfoBean> getRouteInfo(long trainId) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RouteInfoBean> beans = null;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Const.GET_ROUTE_INFO_BY_TRAIN_ID));
			ps.setLong(1, trainId);

			rs = ps.executeQuery();
			beans = new ArrayList<>();

			while (rs.next()) {
				beans.add(extractRouteInfo(rs));
			}
			LOG.debug("Beans obtained ==> " + beans);
		} catch (SQLException e) {
			DbUtils.rollback(conn);
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage());
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		return beans;
	}

	private RouteInfoBean extractRouteInfo(ResultSet rs) throws SQLException {
		RouteInfoBean bean = new RouteInfoBean();
		bean.setStationName(rs.getString("name"));
		bean.setArrTime(rs.getTime("arr_time"));
		bean.setDepTime(rs.getTime("dep_time"));

		return bean;
	}

	// private List<Time> getArrDepTime();

}
