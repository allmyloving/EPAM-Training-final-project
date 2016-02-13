package ua.nure.serdyuk.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.PropertyContainer;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.db.DbUtils;
import ua.nure.serdyuk.db.dao.CarriageDao;
import ua.nure.serdyuk.entity.Carriage;
import ua.nure.serdyuk.entity.Carriage.CarriageType;
import ua.nure.serdyuk.exception.DbException;

public class CarriageDaoMySql implements CarriageDao {

	private static final Logger LOG = Logger.getLogger(CarriageDaoMySql.class);

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
	public boolean delete(Carriage item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Carriage> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Carriage> getAllByTrainId(long trainId, long routeId,
			long routeItemFrom, long routeItemTo) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Carriage> carriages = null;

		try {
			String query = PropertyContainer
					.get(Const.GET_CARRIAGE_INFO_BY_TRAIN_ID);
			query = query.replace("?", String.valueOf(trainId));
			ps = conn.prepareStatement(query);
			// ps = conn.prepareStatement("select carriage.id, tag, name,
			// seat_num from carriage join carriage_type on
			// type_id=carriage_type.id where train_id=?");
			LOG.debug(ps);
			// ps.setLong(1, trainId);

			rs = ps.executeQuery();

			carriages = new ArrayList<>();

			while (rs.next()) {
				carriages.add(extract(conn, rs, routeId, routeItemFrom,
						routeItemTo, trainId));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage());
		} finally {
			DbUtils.close(conn, ps, rs);
		}

		return carriages;
	}

	private Carriage extract(Connection conn, ResultSet rs, long routeId,
			long routeItemFrom, long routeItemTo, long trainId)
					throws SQLException {
		Carriage c = new Carriage();
		PreparedStatement ps = null;
		ResultSet rsSeats = null;

		try {
			c.setId(rs.getLong("id"));
			c.setCarTypeId(rs.getInt("type_id"));
			c.setTag(rs.getString("tag"));

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

			List<Integer> seats = new ArrayList<>();
			while (rsSeats.next()) {
				seats.add(rsSeats.getInt(1));
			}
			c.setSeatsTaken(seats);
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {
			DbUtils.close(null, ps, rsSeats);
		}
		return c;
	}

	@Override
	public Map<Integer, CarriageType> getTypes(long routeItemFrom,
			long routeItemTo, long trainId) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		Map<Integer, CarriageType> types = null;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Const.GET_CARRIAGE_TYPES));

			int k = 1;
			ps.setLong(k++, routeItemFrom);
			ps.setLong(k++, routeItemTo);
			ps.setLong(k++, trainId);

			rs = ps.executeQuery();
			types = new HashMap<>();

			while (rs.next()) {
				CarriageType type = extractType(rs);
				types.put(type.getId(), type);
			}
			LOG.debug(String.format("Types obtained for trainId=%d ==> %s",
					trainId, types));
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage());
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		return types;
	}

	private CarriageType extractType(ResultSet rs) throws SQLException {
		CarriageType type = new CarriageType();
		type.setId(rs.getInt("id"));
		type.setName(rs.getString("name"));
		type.setPrice(rs.getBigDecimal("full_price"));
		type.setSeatNum(rs.getInt("seat_num"));

		return type;
	}

	// public Map<Integer, BigDecimal> getPrice(long trainId,
	// List<Integer> carTypes, long routeItemFrom, long routeItemTo) {
	// Connection conn = DbUtils.getConnection();
	// PreparedStatement ps = null;
	// ResultSet rs = null;
	// Map<Integer, BigDecimal> map = new HashMap<>();
	//
	// try {
	// ps = conn.prepareStatement(PropertyContainer
	// .get(Const.GET_PRICE_BY_TRAIN_ID_CAR_TYPE_AND_ORDINALS));
	//
	// int k = 1;
	// for (int carType : carTypes) {
	// ps.clearParameters();
	//
	// ps.setLong(k++, trainId);
	// ps.setInt(k++, carType);
	// ps.setLong(k++, trainId);
	// ps.setLong(k++, routeItemFrom);
	// ps.setLong(k++, routeItemTo);
	// ps.setLong(k++, trainId);
	//
	// rs = ps.executeQuery();
	//
	// map.put(carType, rs.getBigDecimal("full_price"));
	// }
	// } catch (SQLException e) {
	// LOG.error(e.getMessage());
	// throw new DbException(e.getMessage());
	// } finally {
	// DbUtils.close(conn, ps, rs);
	// }
	// return map;
	// }

}