/**
*
* @author  Franklin Abel (Joshua), 
* @version 1.0
* @since   2020-09-10 
*/
package org.otcframework.dateconverters;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.datatype.XMLGregorianCalendar;

import org.otcframework.dateconverters.exception.DateConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ToLocalDate.
 */
class ToLocalDate extends AbstractDateConversions {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToLocalDate.class);

	/**
	 * To local date.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the local date
	 */
	public static <F> LocalDate toLocalDate(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof String) {
			Date utilDate = DateParserUtils.parseDate((String) date);
			return Instant.ofEpochMilli(utilDate.getTime()).atZone(DEFAULT_ZONE_ID).toLocalDate();
		} 
		if (date instanceof Date) {
			return Instant.ofEpochMilli(((Date) date).getTime()).atZone(DEFAULT_ZONE_ID).toLocalDate();
		}
		if (date instanceof Timestamp) {
			return ((Timestamp) date).toLocalDateTime().toLocalDate();
		}
		if (date instanceof Calendar) {
			Calendar calendar = (Calendar) date;
			TimeZone timeZone = calendar.getTimeZone();
			ZoneId zoneId = timeZone == null ? DEFAULT_ZONE_ID : timeZone.toZoneId();
			return LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
		}
		if (date instanceof XMLGregorianCalendar) {
			return ((XMLGregorianCalendar) date).toGregorianCalendar().toZonedDateTime().toLocalDate();
		}
		if (date instanceof Instant) {
			Instant instant = ((Instant) date);
			return instant.atZone(DEFAULT_ZONE_ID).toLocalDate();
		}
		if (date instanceof LocalDate) {
			return (LocalDate) date;
		}
		if (date instanceof LocalTime) {
			LOGGER.warn("No date information available to convert to LocalDate. Returning null.");
			return null;
		}
		if (date instanceof LocalDateTime) {
			return ((LocalDateTime) date).toLocalDate();
		}
		if (date instanceof ZonedDateTime) {
			return ((ZonedDateTime) date).toLocalDate();
		}
		if (date instanceof OffsetDateTime) {
			return ((OffsetDateTime) date).toZonedDateTime().toLocalDate();
		}
		if (date instanceof org.joda.time.Instant) {
			Instant instant = Instant.ofEpochMilli(((org.joda.time.Instant) date).getMillis());
			return instant.atZone(DEFAULT_ZONE_ID).toLocalDate();
		}
		if (date instanceof org.joda.time.DateTime) {
			Instant instant = Instant.ofEpochMilli(((org.joda.time.DateTime) date).getMillis());
			return instant.atZone(DEFAULT_ZONE_ID).toLocalDate();
		}
		if (date instanceof org.joda.time.LocalDate) {
			Instant instant = Instant.ofEpochMilli(((org.joda.time.LocalDate) date).toDateTimeAtStartOfDay().getMillis());
			return instant.atZone(DEFAULT_ZONE_ID).toLocalDate();
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to LocalDate. Returning null.");
			return null;
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			Instant instant = Instant.ofEpochMilli(((org.joda.time.LocalDateTime) date).toDateTime().getMillis());
			return instant.atZone(DEFAULT_ZONE_ID).toLocalDate();
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to LocalDate");
	}

	/**
	 * To local date.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @return the local date
	 */
	public static LocalDate toLocalDate(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return LocalDate.parse(dateString, formatter);
		} catch (Exception e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to LocalDate", e);
		}
	}

}