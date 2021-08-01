/**
*
* @author  Franklin Abel (Joshua), 
* @version 1.0
* @since   2020-09-10 
*/
package org.otcframework.dateconverters;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.otcframework.dateconverters.exception.DateConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ToSqlDate.
 */
class ToSqlDate extends AbstractDateConversions {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToSqlDate.class);

	/**
	 * To sql date.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the date
	 */
	public static <F> Date toSqlDate(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof Date) {
			return (Date) date;
		}
		if (date instanceof String) {
			return new Date(DateParserUtils.parseDate((String) date).getTime());
		} 
		if (date instanceof java.util.Date || date instanceof Timestamp) {
			return new Date(((java.util.Date) date).getTime());
		}
		if (date instanceof Calendar) {
			return new Date(((Calendar) date).getTimeInMillis());
		}
		if (date instanceof XMLGregorianCalendar) {
			return new Date(((XMLGregorianCalendar) date).toGregorianCalendar().getTimeInMillis());
		}
		if (date instanceof Instant) {
			return new Date(((Instant) date).toEpochMilli());
		}
		if (date instanceof LocalDate) {
			return new Date(((LocalDate) date).atStartOfDay(DEFAULT_ZONE_ID).toInstant().toEpochMilli());
		}
		if (date instanceof LocalTime) {
			LOGGER.warn("No date information available to convert to java.sql.Date. Returning null.");
			return null;
		}
		if (date instanceof LocalDateTime) {
			return new Date(((LocalDateTime) date).atZone(DEFAULT_ZONE_ID).toInstant().toEpochMilli());
		}
		if (date instanceof ZonedDateTime) {
			return new Date(((ZonedDateTime) date).toInstant().toEpochMilli());
		}
		if (date instanceof OffsetDateTime) {
			return Date.valueOf(((OffsetDateTime) date).toLocalDate());
		}
		if (date instanceof org.joda.time.Instant) {
			return new Date(((org.joda.time.Instant) date).getMillis());
		}
		if (date instanceof org.joda.time.DateTime) {
			return new Date(((org.joda.time.DateTime) date).getMillis());
		}
		if (date instanceof org.joda.time.LocalDate) {
			return new Date(((org.joda.time.LocalDate) date).toDate().getTime());
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to java.sql.Date. Returning null.");
			return null;
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			return new Date(((org.joda.time.LocalDateTime) date).toDate().getTime()); 
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to java.sql.Date");
	}

	/**
	 * To sql date.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @return the date
	 */
	public static Date toSqlDate(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			java.util.Date date = new SimpleDateFormat(format).parse((String) dateString);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			return sqlDate;
		} catch (ParseException e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to java.sql.Date", e);
		}
	}

}