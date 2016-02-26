package ua.nure.serdyuk.SummaryTask4.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.entity.RouteItem;
import ua.nure.serdyuk.SummaryTask4.exception.AppException;

public final class DateUtils {

	private static final Logger LOG = Logger.getLogger(DateUtils.class);

	public static java.sql.Date extractDate(String date, String formatString) {
		if (date == null || date.isEmpty()) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		java.util.Date parsed;
		try {
			parsed = format.parse(date);
		} catch (ParseException e) {
			LOG.error(e.getMessage());
			throw new AppException(e.getMessage(), e);
		}

		return new java.sql.Date(parsed.getTime());
	}

	public static java.util.Date extractDate(java.sql.Date date, Time time) {
		java.util.Date t = new java.util.Date(time.getTime());

		Calendar dCal = Calendar.getInstance();
		if (date != null) {
			java.util.Date d = new java.util.Date(date.getTime());
			dCal.setTime(d);
		}

		Calendar tCal = Calendar.getInstance();
		tCal.setTime(t);

		dCal.set(Calendar.HOUR_OF_DAY, tCal.get(Calendar.HOUR_OF_DAY));
		dCal.set(Calendar.MINUTE, tCal.get(Calendar.MINUTE));
		dCal.set(Calendar.SECOND, tCal.get(Calendar.SECOND));

		return dCal.getTime();
	}

	public static void increaseDays(Date date, int days) {
		LOG.debug(String.format("Days increased by %d", days));

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		date.setTime(cal.getTime().getTime());
	}

	public static String getDuration(Date depDate, Date arrDate) {
		int h = (int) getDateDiff(arrDate, depDate, TimeUnit.HOURS);
		int m = (int) getDateDiff(arrDate, depDate, TimeUnit.MINUTES) - 60 * h;

		LOG.debug(String.format("h=%d, m=%d", h, m));
		return String.format("%d:%d", h, m);
	}

	public static Date getArrivalDate(Date arrTime, Date depDate,
			List<RouteItem> routeItems) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(arrTime);
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTime(depDate);

		cal.set(Calendar.DATE, calFrom.get(Calendar.DATE));
		cal.set(Calendar.MONTH, calFrom.get(Calendar.MONTH));
		cal.set(Calendar.YEAR, calFrom.get(Calendar.YEAR));

		Date arrDate = cal.getTime();
		int days = 0;

		for (int i = 0; i < routeItems.size() - 1; i++) {
			Date dTime = routeItems.get(i).getDepartureTime();
			Date aTime = routeItems.get(i + 1).getArrivalTime();

			if (dTime.after(aTime)) {
				LOG.debug(String.format("%s is after %s", dTime.toString(),
						aTime.toString()));
				days++;
			}
		}

		if (days > 0) {
			DateUtils.increaseDays(arrDate, days);
		}

		return arrDate;
	}

	public static Date today() {
		return Calendar.getInstance().getTime();
	}

	public static Time getTime(Date date) {
		return (date == null) ? null : new Time(date.getTime());
	}

	private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date1.getTime() - date2.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}
