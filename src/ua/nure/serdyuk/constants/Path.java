package ua.nure.serdyuk.constants;

public final class Path {

	public static final String SQL_RES = "config";

	public static final String LOG4J_CONFIG = "WEB-INF/log4j.properties";

	public static final String LOGIN_VIEW = "/WEB-INF/jsp/user/login.jsp";

	public static final String LOGIN_VIEW_COMMAND = "/controller?command=loginView";

	public static final String INDEX_VIEW = "/index.jsp";

	public static final String SIGN_UP_VIEW = "/WEB-INF/jsp/user/signUp.jsp";

	public static final String SIGN_UP_VIEW_COMMAND = "/controller?command=signUpView";

	public static final String ROUTE_INFO_VIEW = "/WEB-INF/jsp/displayRouteInfo.jsp";

	public static final String FREE_SEATS_VIEW = "/WEB-INF/jsp/displayFreeSeats.jsp";

	public static final String ORDER_TICKET_VIEW = "/WEB-INF/jsp/orderTicket.jsp";

	public static final String PAYMENT_SUCCESSFUL_VIEW = "/WEB-INF/jsp/paymentSuccess.jsp";

	public static final String PAYMENT_SUCCESSFUL_VIEW_COMMAND = "/controller?command=paymentSuccessfulView";

	public static final String ADMIN_VIEW = "/WEB-INF/jsp/admin/adminView.jsp";
	
	public static final String STATIONS_VIEW = "/WEB-INF/jsp/admin/stationsView.jsp";
	
	public static final String STATIONS_VIEW_COMMAND = "/controller?command=viewStations";
	
	public static final String ADD_ROUTE_VIEW = "/WEB-INF/jsp/admin/addRoute.jsp";
	
	public static final String ADD_ROUTE_VIEW_COMMAND = "/controller?command=addRouteView";
	
	public static final String ADD_TRAIN_VIEW_COMMAND = "/controller?command=addTrainView";
	
	public static final String ADD_TRAIN_VIEW = "/WEB-INF/jsp/admin/addTrain.jsp";

	public static final String ERROR_VIEW = "/WEB-INF/jsp/error/error_main.jsp";

	public static final String ERROR_404_VIEW = "/WEB-INF/jsp/error/error_404.jsp";

}
