/**
*
* @author  Franklin Abel (Joshua), 
* @version 1.0
* @since   2020-09-10 
*/
package org.otcl.dateconverters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.otcl.dateconverters.exception.DateConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ToCalendar.
 */
class ToCalendar extends AbstractDateConversions {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToCalendar.class);

	/**
	 * To calendar.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the calendar
	 */
	public static <F> Calendar toCalendar(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof String) {
			return DateParserUtils.parseCalendar((String) date);
		} 
		if (date instanceof Date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date) date);
			return calendar;
		}
		if (date instanceof Calendar) {  //date instanceof GregorianCalendar ||
			return (Calendar) date;
		} 
		if (date instanceof XMLGregorianCalendar) {
			return ((XMLGregorianCalendar) date).toGregorianCalendar();
		}
		if (date instanceof Instant) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(((Instant) date).toEpochMilli());
			return calendar;
		}
		if (date instanceof LocalDate) {
			return GregorianCalendar.from(((LocalDate) date).atStartOfDay(DEFAULT_ZONE_ID));
		}
		if (date instanceof LocalTime) {
			LOGGER.warn("No date information available to convert to Calendar.");
			return null;
		}
		if (date instanceof LocalDateTime) {
			return GregorianCalendar.from(((LocalDateTime) date).atZone(DEFAULT_ZONE_ID));
		}
		if (date instanceof ZonedDateTime) {
			return GregorianCalendar.from(((ZonedDateTime) date));
		}
		if (date instanceof OffsetDateTime) {
			return GregorianCalendar.from(((OffsetDateTime) date).toZonedDateTime());
		}
		if (date instanceof org.joda.time.Instant) {
			return (((org.joda.time.Instant) date).toDateTime().toCalendar(DEFAULT_LOCALE));
		}
		if (date instanceof org.joda.time.DateTime) {
			return ((org.joda.time.DateTime) date).toCalendar(DEFAULT_LOCALE);
		}
		if (date instanceof org.joda.time.LocalDate) {
			return (((org.joda.time.LocalDate) date).toDateTime(org.joda.time.LocalTime.MIDNIGHT)).toCalendar(DEFAULT_LOCALE);
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to java.sql.Date. Returning null.");
			return null;
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			return (((org.joda.time.LocalDateTime) date).toDateTime().toCalendar(DEFAULT_LOCALE));
		}
		throw new DateConverterException("", "Date conversion error! Unable to convert " + date.getClass().getName() +
				" to Calendar");
	}

	/**
	 * To calendar.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @return the calendar
	 */
	public static Calendar toCalendar(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new SimpleDateFormat(format).parse((String) dateString));
			return calendar;
		} catch (ParseException e) {
			throw new DateConverterException(
					"Calendar conversion error! Unable to convert " + dateString + " to Calendar.", e);
		}
	}

}