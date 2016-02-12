package ua.nure.serdyuk.constants;

public final class Const {
	// attributes
	public static final String EMAIL = "email";

	public static final String PASSWORD = "password";

	public static final String PASSWORD_REPEAT = "repPassword";

	public static final String FIRST_NAME = "firstName";

	public static final String LAST_NAME = "lastName";

	public static final String ROLE = "role";
	
	public static final String STATION_FROM = "stationFrom";
	
	public static final String STATION_TO = "stationTo";

	public static final String CURRENT_USER = "currentUser";

	public static final String CARRIAGE_LIST = "carriageList";

	public static final String USER_SERVICE = "userService";

	public static final String STATION_SERVICE = "stationService";

	public static final String ROUTE_SERVICE = "routeService";

	public static final String ROUTE_ITEM_SERVICE = "routeItemService";

	public static final String TRAIN_INFO_SERVICE = "trainInfoService";

	public static final String ROUTE_INFO_SERVICE = "routeInfoService";

	public static final String CARRIAGE_SERVICE = "carriageService";

	public static final String CURRENT_LOCALE = "currentLocale";

	public static final String CURRENT_LOCALE_LOCALE = "currentLocaleLocale";

	public static final String NEW_LOCALE = "lang";

	public static final String DEFAULT_LOCALE = "defaultLocale";

	public static final String BUNDLE_BASENAME = "bundleBasename";

	public static final String LOCALE_LIST = "localeList";

	public static final String LOCALE_LIST_LOCALE = "localeListLocale";

	public static final String TRAIN_INFO_BEANS = "trainBeans";

	public static final String ROUTE_INFO_BEANS = "routeInfoBeans";

	// sql keys
	public static final String GET_USER_BY_ID = "SQL_SELECT_USER_BY_ID";

	public static final String GET_USER_BY_EMAIL = "SQL_SELECT_USER_BY_EMAIL";

	public static final String INSERT_USER = "SQL_INSERT_USER";

	public static final String GET_STATION_BY_NAME = "SQL_GET_STATION_BY_NAME";

	public static final String GET_ALL_STATIONS = "SQL_GET_ALL_STATIONS";

	public static final String GET_ALL_STATIONS_LIKE = "SQL_GET_ALL_STATIONS_LIKE";

	public static final String GET_ROUTE_INFO_BY_TRAIN_ID = "SQL_GET_ROUTE_INFO_BY_TRAIN_ID";

	public static final String GET_ALL_ROUTE_ITEMS_BY_TRAIN_ID_FROM_TO = "SQL_GET_ALL_ROUTE_ITEMS_BY_TRAIN_ID_FROM_TO";

	public static final String GET_ORDINALS_BY_TRAIN_ID_STATIONS = "SQL_SELECT_ORDINAL_BY_TRAIN_ID_STATIONS";

	public static final String GET_CARRIAGE_BY_ROUTE_ID = "SQL_SELECT_CARRIAGE_BY_ROUTE_ID";

	public static final String GET_ROUTE_BY_DATE = "SQL_SELECT_ROUTE_BY_DATE";

	public static final String GET_ROUTE_BY_STATIONS_AND_DATE = "SQL_SELECT_ROUTE_BY_STATIONS_AND_DATE";

	public static final String GET_START_END_DATE_BY_TRAIN_ID = "SQL_SELECT_START_END_BY_TRAIN_ID";

	public static final String GET_ARR_DEP_TIME_BY_TRAIN_ID_AND_STATIONS = "SQL_SELECT_ARR_DEP_TIME_BY_TRAIN_ID_AND_STATIONS";

	public static final String GET_TRAIN_INFO_BY_TRAIN_ID_AND_ROUTE = "SQL_GET_TRAIN_INFO_BY_TRAIN_ID_AND_ROUTE";

	public static final String GET_CARRIAGE_INFO_BY_TRAIN_ID = "SQL_GET_CARRIAGE_INFO_BY_TRAIN_ID";

	public static final String GET_TAKEN_SEATS_BY_CAR_ID_AND_ROUTE_ID = "SQL_GET_TAKEN_SEATS_BY_CAR_ID_AND_ROUTE_ID";

	public static final String GET_PRICE_BY_TRAIN_ID_CAR_TYPE_AND_ORDINALS = "SQL_GET_PRICE_BY_TRAIN_ID_CAR_TYPE_AND_ORDINALS";
	
	public static final String GET_CARRIAGE_TYPES = "SQL_GET_CARRIAGE_TYPES";

	public static final String INSERT_ROUTE = "SQL_INSERT_ROUTE";

	public static final String INSERT_TICKET = "SQL_INSERT_TICKET";

	// regex
	public static final String REGEX_EMAIL = "^\\w+@\\w+\\.\\w+$";

	public static final String REGEX_PASSWORD = "^\\w{4,}$";

	public static final String REGEX_NAME = "^(\\p{javaUpperCase}[a-zà-ÿ]*\\s?)+$";

	// stuff
	public static final String CLIENT_DATE_FORMAT = "MM/dd/yyyy";
}