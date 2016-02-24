package ua.nure.serdyuk.SummaryTask4.db.factory;

public class AbstractFactory {

	public static DaoFactory getDaoFactory(String dbms) {
		switch (dbms.toLowerCase()) {
		case "mysql":
			return new MySqlDaoFactory();

		default:
			return new MySqlDaoFactory();
		}
	}

}
