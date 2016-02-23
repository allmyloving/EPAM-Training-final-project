package ua.nure.serdyuk.SummaryTask4.db.dao.mysql;

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

import ua.nure.serdyuk.SummaryTask4.constants.Const;
import ua.nure.serdyuk.SummaryTask4.db.DbUtils;
import ua.nure.serdyuk.SummaryTask4.db.dao.TrainBeanDao;
import ua.nure.serdyuk.SummaryTask4.entity.bean.RouteBean;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TrainBean;
import ua.nure.serdyuk.SummaryTask4.exception.DbException;
import ua.nure.serdyuk.SummaryTask4.util.DateUtils;
import ua.nure.serdyuk.SummaryTask4.util.PropertyContainer;

public class TrainBeanDaoMySql implements TrainBeanDao {

	private static final Logger LOG = Logger.getLogger(TrainBeanDaoMySql.class);

	private static final String NAME = "name";

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
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, s, rs);
		}
		return beans;
	}

	private TrainBean extractBean(ResultSet rs) throws SQLException {
		TrainBean bean = new TrainBean();
		bean.setTrainId(rs.getLong("train_id"));
		bean.setTrainTag(rs.getString("tag"));
		bean.setStationFrom(rs.getString(NAME));

		if (rs.next()) {
			bean.setStationTo(rs.getString(NAME));
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
					.get(Const.GET_TRAIN_INFO_BY_TRAIN_ID_ROUTE_STATIONS));
			int k = 1;
			ps.setLong(k++, trainId);
			ps.setLong(k++, routeId);
			ps.setLong(k++, stFromId);
			ps.setLong(k++, stToId);

			LOG.debug("executing ==> " + ps);

			rs = ps.executeQuery();

			bean = new TrainBean();

			if (rs.next()) {
				bean.setTrainTag(rs.getString("tag"));
				bean.setStationFrom(rs.getString(NAME));
				bean.setRouteItemIdFrom(rs.getLong("id"));

				Time depTime = rs.getTime("dep_time");
				java.sql.Date depDate = rs.getDate("date");
				java.util.Date depDateUtil = DateUtils.extractDate(depDate,
						depTime);
				LOG.debug(String.format("dep date=%s; dep date util=%s",
						depDate.toString(), depDateUtil.toString()));

				bean.setDepDate(depDateUtil);
				bean.setArrDate(
						DateUtils.extractDate(depDate, rs.getTime("arr_time")));

				if (rs.next()) {
					bean.setStationTo(rs.getString(NAME));
					bean.setRouteItemIdTo(rs.getLong("id"));
				}
			}
			LOG.debug(String.format("Bean  ==> %s", bean.toString()));

		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
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
			throw new DbException(e.getMessage(), e);
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
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		return beans;
	}

	private RouteBean extractRouteInfo(ResultSet rs) throws SQLException {
		RouteBean bean = new RouteBean();
		bean.setStationName(rs.getString(NAME));
		bean.setArrTime(rs.getTime("arr_time"));
		bean.setDepTime(rs.getTime("dep_time"));

		return bean;
	}
}
