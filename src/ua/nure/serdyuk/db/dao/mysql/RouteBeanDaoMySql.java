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
import ua.nure.serdyuk.db.dao.RouteBeanDao;
import ua.nure.serdyuk.entity.bean.RouteBean;
import ua.nure.serdyuk.exception.DbException;

public class RouteBeanDaoMySql implements RouteBeanDao {

	private static final Logger LOG = Logger.getLogger(RouteBeanDaoMySql.class);

	@Override
	public boolean create(RouteBean item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public RouteBean get(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean update(RouteBean item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(RouteBean item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<RouteBean> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<RouteBean> getAllByTrainId(long trainId) {
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
				beans.add(extract(rs));
			}
			
			LOG.debug("beans obtained ==> " + beans);
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DbException(e.getMessage());
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		return beans;
	}

	private RouteBean extract(ResultSet rs) throws SQLException {
		RouteBean bean = new RouteBean();
		bean.setArrTime(rs.getTime("arr_time"));
		bean.setDepTime(rs.getTime("dep_time"));
		bean.setStationName(rs.getString("name"));

		return bean;
	}

}
