package ua.nure.serdyuk.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.PropertyContainer;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.db.DbUtils;
import ua.nure.serdyuk.db.dao.RouteDao;
import ua.nure.serdyuk.entity.Route;
import ua.nure.serdyuk.exception.DbException;

public class RouteDaoMySql implements RouteDao {

	private static final Logger LOG = Logger.getLogger(RouteDaoMySql.class);

	@Override
	public boolean create(Route item) {
		throw new UnsupportedOperationException();
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
	public boolean delete(Route item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Route> getAll() {
		throw new UnsupportedOperationException();
	}

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
}
