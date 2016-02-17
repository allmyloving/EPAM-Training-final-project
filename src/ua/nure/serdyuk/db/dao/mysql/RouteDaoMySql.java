package ua.nure.serdyuk.db.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.db.DbUtils;
import ua.nure.serdyuk.db.dao.RouteDao;
import ua.nure.serdyuk.entity.Route;
import ua.nure.serdyuk.exception.DbException;
import ua.nure.serdyuk.util.PropertyContainer;

public class RouteDaoMySql implements RouteDao {

	private static final Logger LOG = Logger.getLogger(RouteDaoMySql.class);

	@Override
	public List<Route> getAllByStationsAndDate(long stationFromId,
			long stationToId, java.sql.Date date) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Route> routes = null;

		try {
			ps = conn.prepareStatement(PropertyContainer
					.get(Const.GET_ROUTE_BY_STATIONS_AND_DATE));
			int k = 1;
			ps.setLong(k++, stationFromId);
			ps.setLong(k++, stationToId);
			ps.setDate(k++, date);

			rs = ps.executeQuery();

			routes = new ArrayList<>();
			while (rs.next()) {
				routes.add(extract(rs));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage());
		}
		return routes;
	}

	private Route extract(ResultSet rs) throws SQLException {
		Route route = new Route();
		route.setId(rs.getInt("id"));
		route.setDate(rs.getDate("date"));
		route.setTrainId(rs.getLong("train_id"));

		return route;
	}

	@Override
	public boolean create(Route item) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Const.SQL_INSERT_ROUTE),
					Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			ps.setDate(k++, item.getDate());
			ps.setLong(k++, item.getTrainId());

			result = ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				item.setId(rs.getInt(1));
			}

			conn.commit();
		} catch (SQLException e) {
			DbUtils.rollback(conn);
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage());
		} finally {
			DbUtils.close(conn, ps, rs);
		}

		return result != 0;
	}

	@Override
	public Route get(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean update(Route item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Route> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Route> getAllByDates(Date from, Date to) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Route> routes = null;

		try {
			ps = conn.prepareStatement(PropertyContainer
					.get(Const.SQL_GET_ALL_ROUTES_BY_DATES));
			int k = 1;
			ps.setDate(k++, from);
			ps.setDate(k++, to);

			rs = ps.executeQuery();

			routes = new ArrayList<>();
			while (rs.next()) {
				routes.add(extract(rs));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage());
		}
		return routes;
	}
}
