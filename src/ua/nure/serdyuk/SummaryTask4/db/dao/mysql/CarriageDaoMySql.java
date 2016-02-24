package ua.nure.serdyuk.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.constants.Const;
import ua.nure.serdyuk.SummaryTask4.db.DbUtils;
import ua.nure.serdyuk.SummaryTask4.db.dao.CarriageDao;
import ua.nure.serdyuk.SummaryTask4.entity.Carriage;
import ua.nure.serdyuk.SummaryTask4.entity.CarriageType;
import ua.nure.serdyuk.SummaryTask4.exception.DbException;
import ua.nure.serdyuk.SummaryTask4.util.PropertyContainer;

public class CarriageDaoMySql implements CarriageDao {

	private static final Logger LOG = Logger.getLogger(CarriageDaoMySql.class);

	@Override
	public List<Carriage> getAllByTrainIdRouteItems(long trainId, long routeId,
			long routeItemFrom, long routeItemTo) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Carriage> carriages = null;

		try {
			ps = conn.prepareStatement(PropertyContainer
					.get(Const.GET_CARRIAGE_INFO_BY_TRAIN_ID_ROUTE_ITEMS));
			int k = 1;
			ps.setLong(k++, routeItemFrom);
			ps.setLong(k++, routeItemTo);
			ps.setLong(k++, trainId);
			LOG.debug("executing " + ps);

			rs = ps.executeQuery();

			carriages = new ArrayList<>();

			while (rs.next()) {
				carriages.add(extract(conn, rs, routeId, routeItemFrom,
						routeItemTo, trainId));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, ps, rs);
		}

		return carriages;
	}

	private Carriage extract(Connection conn, ResultSet rs, long routeId,
			long routeItemFrom, long routeItemTo, long trainId)
					throws SQLException {
		Carriage c = null;
		PreparedStatement ps = null;
		ResultSet rsSeats = null;
		try {
			c = extract(rs);

			ps = conn.prepareStatement(PropertyContainer
					.get(Const.GET_TAKEN_SEATS_BY_CAR_ID_AND_ROUTE_ID));
			int k = 1;
			ps.setLong(k++, c.getId());
			ps.setLong(k++, routeId);
			ps.setLong(k++, trainId);
			ps.setLong(k++, routeItemTo);
			ps.setLong(k++, trainId);
			ps.setLong(k++, routeItemFrom);

			LOG.debug("seats taken ps " + ps);

			rsSeats = ps.executeQuery();

			c.setSeats(getFreeSeats(rsSeats));

		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new SQLException(e.getMessage(), e);
		} finally {
			DbUtils.close(null, ps, rsSeats);
		}
		return c;
	}

	@Override
	public boolean create(Carriage item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Carriage get(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean update(Carriage item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Carriage> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean createAll(List<Carriage> carriages) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Const.SQL_CREATE_CARRIAGE));

			for (Carriage c : carriages) {
				int k = 1;
				ps.setString(k++, c.getTag());
				ps.setBigDecimal(k++, c.getPrice());
				ps.setInt(k++, c.getCarTypeId());
				ps.setInt(k++, c.getTrainId());
				LOG.debug("executing " + ps);
				ps.addBatch();
			}
			result = DbUtils.isBatchSuccessful(ps.executeBatch());

			conn.commit();
		} catch (SQLException e) {
			DbUtils.rollback(conn);
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, ps, rs);
		}

		return result;
	}

	@Override
	public List<Carriage> getAllByTrainId(long trainId) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Carriage> carriages = null;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Const.GET_CARRIAGE_INFO_BY_TRAIN_ID));
			ps.setLong(1, trainId);

			rs = ps.executeQuery();

			carriages = new ArrayList<>();

			while (rs.next()) {
				carriages.add(extract(rs));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, ps, rs);
		}

		return carriages;
	}

	private Map<Integer, Boolean> getFreeSeats(ResultSet rs) throws SQLException {
		List<Integer> seats = new ArrayList<>();
		while (rs.next()) {
			seats.add(rs.getInt(1));
		}
		Map<Integer, Boolean> seatsMap = new HashMap<>();
		for (int s : seats) {
			seatsMap.put(s, true);
		}
		return seatsMap;
	}

	private Carriage extract(ResultSet rs) throws SQLException {
		Carriage c = new Carriage();
		c.setId(rs.getInt("id"));
		c.setCarTypeId(rs.getInt("type_id"));
		c.setPrice(rs.getBigDecimal("price"));
		c.setTag(rs.getString("tag"));

		CarriageType ct = new CarriageType();
		ct.setId(rs.getInt("type_id"));
		ct.setName(rs.getString("name"));
		ct.setSeatNum(rs.getInt("seat_num"));

		c.setType(ct);

		return c;
	}
}