package ua.nure.serdyuk.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.db.DbUtils;
import ua.nure.serdyuk.db.dao.RouteItemDao;
import ua.nure.serdyuk.entity.Route;
import ua.nure.serdyuk.entity.RouteItem;
import ua.nure.serdyuk.exception.DbException;
import ua.nure.serdyuk.util.PropertyContainer;

public class RouteItemDaoMySql implements RouteItemDao {

	private static final Logger LOG = Logger.getLogger(RouteItemDaoMySql.class);

	@Override
	public List<RouteItem> getAllByStations(long trainId, long stFromId,
			long stToId) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RouteItem> items;

		try {
			ps = conn.prepareStatement(PropertyContainer
					.get(Const.GET_ALL_ROUTE_ITEMS_BY_TRAIN_ID_FROM_TO));
			int k = 1;
			ps.setLong(k++, trainId);
			ps.setLong(k++, stFromId);
			ps.setLong(k++, trainId);
			ps.setLong(k++, stToId);
			ps.setLong(k++, trainId);

			rs = ps.executeQuery();

			items = new ArrayList<>();
			while (rs.next()) {
				items.add(extract(rs));
			}
		} catch (SQLException ex) {
			DbUtils.rollback(conn);
			LOG.error(ex.getMessage());
			throw new DbException(ex.getMessage());
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		return items;
	}

	private RouteItem extract(ResultSet rs) throws SQLException {
		RouteItem item = new RouteItem();
		item.setId(rs.getLong("id"));
		item.setArrivalTime(rs.getTime("arr_time"));
		item.setDepartureTime(rs.getTime("dep_time"));
		item.setOrdinal(rs.getInt("ordinal"));
		item.setTrainId(rs.getInt("train_id"));
		item.setStationId(rs.getInt("station_id"));

		return item;
	}

	@Override
	public boolean create(RouteItem item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public RouteItem get(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean update(RouteItem item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<RouteItem> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<RouteItem> getAll(long routeId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean createAll(List<RouteItem> items) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Const.SQL_CREATE_ROUTE_ITEM));

			for (RouteItem ri : items) {
				int k = 1;
				ps.setObject(k++, getTime(ri.getArrivalTime()), Types.TIME);
				ps.setObject(k++, getTime(ri.getDepartureTime()), Types.TIME);
				ps.setInt(k++, ri.getOrdinal());
				ps.setLong(k++, ri.getTrainId());
				ps.setLong(k++, ri.getStationId());
				ps.addBatch();
			}
			result = DbUtils.isBatchSuccessful(ps.executeBatch());

			conn.commit();
		} catch (SQLException e) {
			DbUtils.rollback(conn);
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage());
		} finally {
			DbUtils.close(conn, ps, rs);
		}

		return result;
	}

	private Time getTime(Date date) {
		return (date == null) ? null : new Time(date.getTime());
	}

}
