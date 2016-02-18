package ua.nure.serdyuk.db.dao.mysql;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.db.DbUtils;
import ua.nure.serdyuk.db.dao.TrainBeanDao;
import ua.nure.serdyuk.entity.bean.RouteBean;
import ua.nure.serdyuk.entity.bean.TrainBean;
import ua.nure.serdyuk.exception.DbException;
import ua.nure.serdyuk.util.DateUtils;
import ua.nure.serdyuk.util.PropertyContainer;

public class TrainBeanDaoMySql implements TrainBeanDao {

	private static final Logger LOG = Logger.getLogger(TrainBeanDaoMySql.class);

	@Override
	public boolean create(TrainBean item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public TrainBean get(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean update(TrainBean item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<TrainBean> getAll() {
		Connection conn = DbUtils.getConnection();
		Statement s = null;
		ResultSet rs = null;
		List<TrainBean> beans = null;

		try {
			s = conn.createStatement();
			rs = s.executeQuery(
					PropertyContainer.get(Const.SQL_GET_ALL_TRAINS));

			beans = new ArrayList<>();
			while (rs.next()) {
				beans.add(extractBean(rs));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage());
		} finally {
			DbUtils.close(conn, s, rs);
		}
		return beans;
	}

	private TrainBean extractBean(ResultSet rs) throws SQLException {
		TrainBean bean = new TrainBean();
		bean.setTrainId(rs.getLong("train_id"));
		bean.setTrainTag(rs.getString("tag"));
		bean.setStationFrom(rs.getString("name"));

		if (rs.next()) {
			bean.setStationTo(rs.getString("name"));
		}
		return bean;
	}

	@Override
	public TrainBean getFullInfo(long trainId, long stFromId, long stToId,
			long routeId) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		TrainBean bean = null;

		try {
			ps = conn.prepareStatement(PropertyContainer
					.get(Const.GET_TRAIN_INFO_BY_TRAIN_ID_AND_ROUTE));
			int k = 1;
			ps.setLong(k++, trainId);
			ps.setLong(k++, trainId);
			ps.setLong(k++, routeId);

			rs = ps.executeQuery();

			bean = new TrainBean();

			java.sql.Date depDate = null;
			// throw ex?
			if (rs.next()) {
				depDate = rs.getDate("date");
				bean.setTrainTag(rs.getString("tag"));
				bean.setStationFrom(rs.getString("name"));
				bean.setRouteItemIdFrom(rs.getLong("id"));

				if (rs.next()) {
					bean.setStationTo(rs.getString("name"));
					bean.setRouteItemIdTo(rs.getLong("id"));
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
				java.util.Date depDateUtil = DateUtils.extractDate(depDate,
						depTime);

				bean.setDepDate(depDateUtil);
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

	private List<RouteBean> getRouteInfo(long trainId) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RouteBean> beans = null;

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

	private RouteBean extractRouteInfo(ResultSet rs) throws SQLException {
		RouteBean bean = new RouteBean();
		bean.setStationName(rs.getString("name"));
		bean.setArrTime(rs.getTime("arr_time"));
		bean.setDepTime(rs.getTime("dep_time"));

		return bean;
	}
}
