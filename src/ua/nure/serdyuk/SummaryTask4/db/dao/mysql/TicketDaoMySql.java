package ua.nure.serdyuk.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.constants.Const;
import ua.nure.serdyuk.SummaryTask4.db.DbUtils;
import ua.nure.serdyuk.SummaryTask4.db.dao.TicketDao;
import ua.nure.serdyuk.SummaryTask4.entity.Carriage;
import ua.nure.serdyuk.SummaryTask4.entity.Station;
import ua.nure.serdyuk.SummaryTask4.entity.Ticket;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TicketOrderBean;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TrainBean;
import ua.nure.serdyuk.SummaryTask4.exception.DbException;
import ua.nure.serdyuk.SummaryTask4.util.PropertyContainer;

public class TicketDaoMySql implements TicketDao {

	private static final Logger LOG = Logger.getLogger(TicketDaoMySql.class);

	@Override
	public boolean create(Ticket ticket) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int res = 0;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Const.SQL_INSERT_TICKET),
					Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			ps.setString(k++, ticket.getFirstName());
			ps.setString(k++, ticket.getLastName());
			ps.setInt(k++, ticket.getSeatNum());
			ps.setBigDecimal(k++, ticket.getPrice());
			ps.setTimestamp(k++, new Timestamp(ticket.getDepDate().getTime()));
			ps.setTimestamp(k++, new Timestamp(ticket.getArrDate().getTime()));
			ps.setLong(k++, ticket.getUserId());
			ps.setObject(k++, ticket.getDiscountTypeId(), Types.TINYINT);
			ps.setObject(k++, ticket.getStatusId(), Types.TINYINT);
			ps.setLong(k++, ticket.getCarriageId());
			ps.setLong(k++, ticket.getRouteId());
			ps.setLong(k++, ticket.getRouteDepId());
			ps.setLong(k++, ticket.getRouteArrId());

			LOG.debug("Ticket statement ==> " + ps);

			res = ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				ticket.setId(rs.getLong(1));
			}
			conn.commit();
		} catch (SQLException e) {
			DbUtils.rollback(conn);

			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		return res != 0;
	}

	@Override
	public Ticket get(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean update(Ticket item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Ticket> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<TicketOrderBean> getAllByUserId(long userId) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TicketOrderBean> beans = null;

		try {
			ps = conn.prepareStatement(PropertyContainer
					.get(Const.SQL_GET_ALL_TICKETS_BY_USER_ID));
			ps.setLong(1, userId);

			rs = ps.executeQuery();

			beans = new ArrayList<>();
			while (rs.next()) {
				beans.add(extractBean(rs));
			}
			LOG.info(String.format("Tickets found for userId=%d ==> %s", userId,
					beans.toString()));
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, ps, null);
		}
		return beans;
	}

	private TicketOrderBean extractBean(ResultSet rs) throws SQLException {
		TicketOrderBean bean = new TicketOrderBean();
		bean.setTicketId(rs.getLong("id"));
		bean.setFirstName(rs.getString("f_name"));
		bean.setLastName(rs.getString("l_name"));
		bean.setSeatNum(rs.getInt("seat_num"));

		Station s = new Station();
		s.setName(rs.getString("st_from"));
		bean.setStationFrom(s);
		s = new Station();
		s.setName(rs.getString("st_to"));
		bean.setStationTo(s);

		Carriage c = new Carriage();
		c.setPrice(rs.getBigDecimal("price"));
		c.setTag(rs.getString("car_tag"));

		TrainBean trainBean = new TrainBean();
		trainBean.setTrainTag(rs.getString("train_tag"));
		trainBean.setDepDate(rs.getTimestamp("dep_date"));
		trainBean.setArrDate(rs.getTimestamp("arr_date"));

		bean.setCarriage(c);
		bean.setTrainBean(trainBean);

		return bean;
	}

	@Override
	public boolean exists(TicketOrderBean bean) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> seatsTaken = null;
		try {

			ps = conn.prepareStatement(PropertyContainer
					.get(Const.GET_TAKEN_SEATS_BY_CAR_ID_AND_ROUTE_ID));
			int k = 1;
			ps.setLong(k++, bean.getCarriage().getId());
			ps.setLong(k++, bean.getTrainBean().getRouteId());
			ps.setLong(k++, bean.getTrainBean().getTrainId());
			ps.setLong(k++, bean.getTrainBean().getRouteItemIdTo());
			ps.setLong(k++, bean.getTrainBean().getTrainId());
			ps.setLong(k++, bean.getTrainBean().getRouteItemIdFrom());

			rs = ps.executeQuery();

			seatsTaken = new ArrayList<>();
			while (rs.next()) {
				seatsTaken.add(rs.getInt(1));
			}

			LOG.debug(
					String.format("Seats found ==> %s", seatsTaken.toString()));

		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, ps, null);
		}
		return seatsTaken.contains(bean.getSeatNum());
	}

	@Override
	public List<TicketOrderBean> getAllForTomorrow() {
		Connection conn = DbUtils.getConnection();
		Statement s = null;
		ResultSet rs = null;
		List<TicketOrderBean> beans = null;

		try {
			s = conn.createStatement();
			rs = s.executeQuery(
					PropertyContainer.get(Const.SQL_GET_ALL_TOMORROW_TICKETS));

			beans = new ArrayList<>();

			TicketOrderBean bean;
			while (rs.next()) {
				bean = new TicketOrderBean();
				bean.setTicketId(rs.getLong("id"));
				bean.setUserId(rs.getLong("user_id"));
				// dep date bean.set
				beans.add(bean);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, s, rs);
		}
		return beans;
	}

	@Override
	public boolean setNotified(long id) {
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = null;
		int res = 0;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Const.SQL_SET_TICKET_NOTIFIED));
			ps.setLong(1, id);

			res = ps.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			DbUtils.rollback(conn);
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage(), e);
		} finally {
			DbUtils.close(conn, ps, null);
		}
		return res != 0;
	}
}
