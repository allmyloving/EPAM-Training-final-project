package ua.nure.serdyuk.SummaryTask4.web.listener;

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

import ua.nure.serdyuk.SummaryTask4.constants.Const;
import ua.nure.serdyuk.SummaryTask4.constants.Message;
import ua.nure.serdyuk.SummaryTask4.constants.Path;
import ua.nure.serdyuk.SummaryTask4.db.factory.AbstractFactory;
import ua.nure.serdyuk.SummaryTask4.db.factory.DaoFactory;
import ua.nure.serdyuk.SummaryTask4.db.service.impl.CarriageServiceImpl;
import ua.nure.serdyuk.SummaryTask4.db.service.impl.CarriageTypeServiceImpl;
import ua.nure.serdyuk.SummaryTask4.db.service.impl.RouteBeanServiceImpl;
import ua.nure.serdyuk.SummaryTask4.db.service.impl.RouteItemServiceImpl;
import ua.nure.serdyuk.SummaryTask4.db.service.impl.RouteServiceImpl;
import ua.nure.serdyuk.SummaryTask4.db.service.impl.StationServiceMySql;
import ua.nure.serdyuk.SummaryTask4.db.service.impl.TicketServiceImpl;
import ua.nure.serdyuk.SummaryTask4.db.service.impl.TrainBeanServiceImpl;
import ua.nure.serdyuk.SummaryTask4.db.service.impl.TrainServiceImpl;
import ua.nure.serdyuk.SummaryTask4.db.service.impl.UserServiceImpl;

@WebListener
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	private String dbms;

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
			Class.forName(
					"ua.nure.serdyuk.SummaryTask4.command.CommandContainer");
			Class.forName(
					"ua.nure.serdyuk.SummaryTask4.util.PropertyContainer");
			Class.forName("ua.nure.serdyuk.SummaryTask4.db.DbUtils");
		} catch (ClassNotFoundException e) {
			LOG.error(Message.ERR_CONTAINERS_NOT_INITIALIZED);
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
		LOG.info(
				String.format("Locales supported ==> %s", localesL.toString()));
		context.setAttribute(Const.LOCALE_LIST_LOCALE, localesL);

		dbms = context.getInitParameter("dbms");
		LOG.info(String.format("Application is using %s DBMS", dbms));

		LOG.info("Init params loaded");
	}

	private void configureServices(ServletContext context) {
		DaoFactory daoFactory = AbstractFactory.getDaoFactory(dbms);
		LOG.debug(String.format("dao factory ==> %s", daoFactory));

		context.setAttribute(Const.USER_SERVICE,
				new UserServiceImpl(daoFactory.getUserDao()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "User"));

		context.setAttribute(Const.STATION_SERVICE,
				new StationServiceMySql(daoFactory.getStationDao()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "Station"));

		context.setAttribute(Const.ROUTE_SERVICE,
				new RouteServiceImpl(daoFactory.getRouteDao()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "Route"));

		context.setAttribute(Const.ROUTE_ITEM_SERVICE,
				new RouteItemServiceImpl(daoFactory.getRouteItemDao()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "RouteItem"));

		context.setAttribute(Const.TRAIN_BEAN_SERVICE, new TrainBeanServiceImpl(
				daoFactory.getTrainBeanDao(), daoFactory.getRouteItemDao()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "TrainInfo"));

		context.setAttribute(Const.ROUTE_INFO_SERVICE,
				new RouteBeanServiceImpl(daoFactory.getRouteBeanDao()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "RouteInfo"));

		context.setAttribute(Const.CARRIAGE_SERVICE,
				new CarriageServiceImpl(daoFactory.getCarriageDao()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "Carriage"));

		context.setAttribute(Const.TICKET_SERVICE,
				new TicketServiceImpl(daoFactory.getTicketDao()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "Ticket"));

		context.setAttribute(Const.TRAIN_SERVICE,
				new TrainServiceImpl(daoFactory.getTrainDao()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "Train"));

		context.setAttribute(Const.CARRIAGE_TYPE_SERVICE,
				new CarriageTypeServiceImpl(daoFactory.getCarriageTypeDao()));
		LOG.info(String.format(Message.SERVICE_INITIALIZED, "CarriageType"));

	}
}
