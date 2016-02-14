package ua.nure.serdyuk.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.db.DbUtils;
import ua.nure.serdyuk.db.dao.StationDao;
import ua.nure.serdyuk.entity.Station;
import ua.nure.serdyuk.exception.DbException;
import ua.nure.serdyuk.util.PropertyContainer;

public class StationDaoMySql implements StationDao {

	private static final Logger LOG = Logger.getLogger(StationDaoMySql.class);

	@Override
	public boolean create(Station item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Station get(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean update(Station item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(Station item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Station> getAll() {
		throw new UnsupportedOperationException();
//		Connection conn = DbUtils.getConnection();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		List<Station> stations = new ArrayList<>();
//		final String query = PropertyContainer.get(Const.GET_ALL_STATIONS);
//
//		try {
//			ps = conn.prepareStatement(query);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				stations.add(extractStation(rs));
//			}
//		} catch (SQLException e) {
//			DbUtils.rollback(conn);
//			LOG.error(e.getMessage());
//			throw new DbException(e.getMessage(), e);
//		} finally {
//			DbUtils.close(conn, ps, rs);
//		}
//		return stations;
	}

	@Override
	public List<Station> getAll(String filter) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Station> stations = new ArrayList<>();
		String query = (filter != null)
				? PropertyContainer.get(Const.GET_ALL_STATIONS_LIKE)
				: PropertyContainer.get(Const.GET_ALL_STATIONS);

		try {
			if(filter!=null){
				query = query.replace("?", filter);
			}
			ps = conn.prepareStatement(query);
			LOG.debug(String.format("Executing statement %s", ps.toString()));

			rs = ps.executeQuery();
			while (rs.next()) {
				stations.add(extractStation(rs));
			}
		} catch (SQLException e) {
			DbUtils.rollback(conn);
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		return stations;
	}

	@Override
	public Station getByName(String name) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Station station = null;
		final String query = PropertyContainer.get(Const.GET_STATION_BY_NAME);

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, name);

			rs = ps.executeQuery();
			if (rs.next()) {
				station = extractStation(rs);
			}
		} catch (SQLException e) {
			DbUtils.rollback(conn);
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		return station;
	}

	@Override
	public List<Station> getByRouteItems(long routeItemId1, long routeItemId2) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Station> stations = null;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Const.GET_STATIONS_BY_ROUTE_ITEMS));
			int k = 1;
			ps.setLong(k++, routeItemId1);
			ps.setLong(k++, routeItemId2);

			rs = ps.executeQuery();
			stations = new ArrayList<>();
			while (rs.next()) {
				stations.add(extractStation(rs));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage());
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		if (stations.size() != 2) {
			throw new DbException("Unexpected result set.");
		}
		return stations;
	}

	private Station extractStation(ResultSet rs) throws SQLException {
		Station s = new Station();
		s.setId(rs.getLong("id"));
		s.setName(rs.getString("name"));

		return s;
	}

}
