package ua.nure.serdyuk.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.exception.AppException;

public final class DateUtils {
	
	private static final Logger LOG = Logger.getLogger(DateUtils.class);
	
	public static java.sql.Date extractDate(String date){
		SimpleDateFormat format = new SimpleDateFormat(
				Const.CLIENT_DATE_FORMAT);
		java.util.Date parsed;
		try {
			parsed = format.parse(date);
		} catch (ParseException e) {
			LOG.error(e.getMessage());
			throw new AppException(e.getMessage());
		}
		
		return new java.sql.Date(parsed.getTime());
	}
	
	public static java.util.Date extractDate(java.sql.Date date, Time time) {
		java.util.Date d = new java.util.Date(date.getTime());
		java.util.Date t = new java.util.Date(time.getTime());

		Calendar dCal = Calendar.getInstance();
		dCal.setTime(d);

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

	public static Date getDuration(Date depDate, Date arrDate) {
		Calendar cal = Calendar.getInstance();
		int h = (int) getDateDiff(arrDate, depDate, TimeUnit.HOURS);
		int m = (int) getDateDiff(arrDate, depDate, TimeUnit.MINUTES) - 60 * h;
		cal.set(Calendar.HOUR, h);
		cal.set(Calendar.MINUTE, m);

		return cal.getTime();
	}

	private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date1.getTime() - date2.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}