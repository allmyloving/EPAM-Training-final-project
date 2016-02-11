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
import ua.nure.serdyuk.db.dao.StationDao;
import ua.nure.serdyuk.entity.Station;
import ua.nure.serdyuk.exception.DbException;

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
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Station> stations = new ArrayList<>();
		final String query = PropertyContainer.get(Const.GET_ALL_STATIONS);

		try {
			ps = conn.prepareStatement(query);
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
	public List<Station> getAll(String filter) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Station> stations = new ArrayList<>();
		final String query = PropertyContainer.get(Const.GET_ALL_STATIONS_LIKE);

		try {
			ps = conn.prepareStatement(query.replace("?", filter));
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

	private Station extractStation(ResultSet rs) throws SQLException {
		Station s = new Station();
		s.setId(rs.getLong("id"));
		s.setName(rs.getString("name"));

		return s;
	}

}
