/**
*
* @author  Franklin Abel (Joshua), 
* @version 1.0
* @since   2020-09-10 
*/
package org.otcframework.dateconverters;

import java.sql.Time;
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
 * The Class ToLocalTime.
 */
class ToLocalTime extends AbstractDateConversions {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToLocalDate.class);

	/**
	 * To local time.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the local time
	 */
	public static <F> LocalTime toLocalTime(F date) {
		if (date == null || date instanceof java.sql.Date) {
			return null;
		}
		if (date instanceof String) {
			Date utilDate = DateParserUtils.parseDate((String) date);
			return utilDate.toInstant().atZone(DEFAULT_ZONE_ID).toLocalTime();
		} 
		if (date instanceof Date) {
			return Instant.ofEpochMilli(((Date) date).getTime()).atZone(DEFAULT_ZONE_ID).toLocalTime();
		}
		if (date instanceof Timestamp) {
			return ((Timestamp) date).toLocalDateTime().toLocalTime();
		}
		if (date instanceof Time) {
			return ((Time) date).toLocalTime();
		}
		if (date instanceof Calendar) {
			Calendar calendar = (Calendar) date;
			TimeZone timeZone = calendar.getTimeZone();
			ZoneId zoneId = timeZone == null ? DEFAULT_ZONE_ID : timeZone.toZoneId();
			return LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalTime();
		}
		if (date instanceof XMLGregorianCalendar) {
			return ((XMLGregorianCalendar) date).toGregorianCalendar().toZonedDateTime().toLocalTime();
		}
		if (date instanceof Instant) {
			Instant instant = ((Instant) date);
			return instant.atZone(DEFAULT_ZONE_ID).toLocalTime();
		}
		if (date instanceof LocalTime) {
			return (LocalTime) date;
		}
		if (date instanceof LocalDate) {
			LOGGER.warn("No Time information available to convert to LocalTime. Returning null.");
			return null;
		}
		if (date instanceof LocalDateTime) {
			return ((LocalDateTime) date).toLocalTime();
		}
		if (date instanceof ZonedDateTime) {
			return ((ZonedDateTime) date).toLocalTime();
		}
		if (date instanceof OffsetDateTime) {
			return ((OffsetDateTime) date).toZonedDateTime().toLocalTime();
		}
		if (date instanceof org.joda.time.Instant) {
			org.joda.time.DateTime dateTime = ((org.joda.time.Instant) date).toDateTime();
			return LocalTime.of(dateTime.getHourOfDay(), dateTime.getMinuteOfHour(), dateTime.getSecondOfMinute());
		}
		if (date instanceof org.joda.time.LocalDate) {
			org.joda.time.DateTime dateTime = ((org.joda.time.LocalDate) date).toDateTimeAtStartOfDay();
			return LocalTime.of(dateTime.getHourOfDay(), dateTime.getMinuteOfHour(), dateTime.getSecondOfMinute());
		}
		if (date instanceof org.joda.time.LocalTime) {
			org.joda.time.LocalTime localTime = ((org.joda.time.LocalTime) date);
			return LocalTime.of(localTime.getHourOfDay(), localTime.getMinuteOfHour(), localTime.getSecondOfMinute());
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			org.joda.time.LocalDateTime localDateTime = ((org.joda.time.LocalDateTime) date);
			return LocalTime.of(localDateTime.getHourOfDay(), localDateTime.getMinuteOfHour(), localDateTime.getSecondOfMinute());
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to LocalTime");
	}

	/**
	 * To local time.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @return the local time
	 */
	public static LocalTime toLocalTime(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return LocalTime.parse(dateString, formatter);
		} catch (Exception e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to LocalTime", e);
		}
	}

}