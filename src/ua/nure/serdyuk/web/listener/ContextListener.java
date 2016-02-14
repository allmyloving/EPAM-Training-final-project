package ua.nure.serdyuk.web.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.nure.serdyuk.Message;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.dao.mysql.CarriageDaoMySql;
import ua.nure.serdyuk.db.dao.mysql.RouteBeanDaoMySql;
import ua.nure.serdyuk.db.dao.mysql.RouteDaoMySql;
import ua.nure.serdyuk.db.dao.mysql.RouteItemDaoMySql;
import ua.nure.serdyuk.db.dao.mysql.StationDaoMySql;
import ua.nure.serdyuk.db.dao.mysql.TicketDaoMySql;
import ua.nure.serdyuk.db.dao.mysql.TrainInfoDaoMySql;
import ua.nure.serdyuk.db.dao.mysql.UserDaoMySql;
import ua.nure.serdyuk.db.service.impl.CarriageServiceImpl;
import ua.nure.serdyuk.db.service.impl.RouteBeanServiceImpl;
import ua.nure.serdyuk.db.service.impl.RouteItemServiceMySql;
import ua.nure.serdyuk.db.service.impl.RouteServiceMySql;
import ua.nure.serdyuk.db.service.impl.StationServiceMySql;
import ua.nure.serdyuk.db.service.impl.TicketServiceImpl;
import ua.nure.serdyuk.db.service.impl.TrainBeanServiceImpl;
import ua.nure.serdyuk.db.service.impl.UserServiceMySql;

@WebListener
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();

		configureLog4j(context);
		initParams(context);
		loadClasses();
		configureServices(context);
	}

	private void loadClasses() {
		try {
			Class.forName("ua.nure.serdyuk.command.CommandContainer");
			Class.forName("ua.nure.serdyuk.PropertyContainer");
			Class.forName("ua.nure.serdyuk.db.DbUtils");
		} catch (ClassNotFoundException e) {
			LOG.error(Message.ERR_CONTAINERS_NOT_INITIALIZED);
			// throw new AppException(Message.ERR_CONTAINERS_NOT_INITIALIZED,
			// e);
		}
		LOG.debug("Containers initialized successfully");
	}

	private void configureLog4j(ServletContext context) {
		System.out.println("Configuring log4j...");
		try {
			PropertyConfigurator
					.configure(context.getRealPath(Path.LOG4J_CONFIG));
			LOG.debug("Log4j has been initialized");
		} catch (Exception ex) {
			System.out.println(Message.ERR_CANNOT_CONFIGURE_LOG4J);
			// throw new AppException(Message.ERR_CANNOT_CONFIGURE_LOG4J, ex);
		}
	}

	private void initParams(ServletContext context) {
		context.setAttribute(Const.BUNDLE_BASENAME,
				context.getInitParameter(Const.BUNDLE_BASENAME));
		String[] locales = context.getInitParameter(Const.LOCALE_LIST)
				.split("\\s+");
		context.setAttribute(Const.LOCALE_LIST, Arrays.asList(locales));

		List<Locale> localesL = new ArrayList<>();
		for (String l : locales) {
			localesL.add(new Locale(l));
		}
		LOG.debug("Locale list (of Locale's) ==> " + localesL);
		context.setAttribute(Const.LOCALE_LIST_LOCALE, localesL);

		LOG.info("Init params loaded");
	}

	private void configureServices(ServletContext context) {
		// use factory here?
		context.setAttribute(Const.USER_SERVICE,
				new UserServiceMySql(new UserDaoMySql()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "User"));

		context.setAttribute(Const.STATION_SERVICE,
				new StationServiceMySql(new StationDaoMySql()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "Station"));

		context.setAttribute(Const.ROUTE_SERVICE,
				new RouteServiceMySql(new RouteDaoMySql()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "Route"));

		context.setAttribute(Const.ROUTE_ITEM_SERVICE,
				new RouteItemServiceMySql(new RouteItemDaoMySql()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "RouteItem"));

		context.setAttribute(Const.TRAIN_BEAN_SERVICE, new TrainBeanServiceImpl(
				new TrainInfoDaoMySql(), new RouteItemDaoMySql()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "TrainInfo"));

		context.setAttribute(Const.ROUTE_INFO_SERVICE,
				new RouteBeanServiceImpl(new RouteBeanDaoMySql()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "RouteInfo"));

		context.setAttribute(Const.CARRIAGE_SERVICE,
				new CarriageServiceImpl(new CarriageDaoMySql()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "Carriage"));

		context.setAttribute(Const.TICKET_SERVICE,
				new TicketServiceImpl(new TicketDaoMySql()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "Ticket"));

	}
}
