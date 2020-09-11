/**
*
* @author  Franklin Abel (Joshua), 
* @version 1.0
* @since   2020-09-10 
*/
package org.otcl.dateconverters;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.otcl.dateconverters.exception.DateConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ToJodaLocalTime.
 */
class ToJodaLocalTime extends AbstractDateConversions {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToJodaLocalTime.class);

	/**
	 * To local time.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the local time
	 */
	public static <F> LocalTime toLocalTime(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof String) {
			Date utilDate = DateParserUtils.parseDate((String) date);
			return new LocalTime(utilDate);
		} 
		if (date instanceof Date) {
			return new LocalTime((Date) date);
		}
		if (date instanceof Calendar) {
			Calendar calendar = (Calendar) date;
			return new LocalTime(calendar);
		}
		if (date instanceof XMLGregorianCalendar) {
			return new LocalTime(((XMLGregorianCalendar) date).toGregorianCalendar());
		}
		if (date instanceof Instant) {
			return new LocalTime(((Instant) date).toEpochMilli());
		}
		if (date instanceof LocalDate) {
			return new LocalTime(((LocalDate) date).atStartOfDay(DEFAULT_ZONE_ID).toInstant().toEpochMilli());
		}
		if (date instanceof java.time.LocalTime) {
			java.time.LocalTime localTime = (java.time.LocalTime) date;
			return new LocalTime(localTime.getHour(), localTime.getMinute(), localTime.getSecond());
		}
		if (date instanceof LocalDateTime) {
			return new LocalTime(((LocalDateTime) date).toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli());
		}
		if (date instanceof ZonedDateTime) {
			return new LocalTime(((ZonedDateTime) date).toInstant().toEpochMilli());
		}
		if (date instanceof OffsetDateTime) {
			return new LocalTime(((OffsetDateTime) date).toInstant().toEpochMilli());
		}
		if (date instanceof org.joda.time.Instant) {
			return ((org.joda.time.Instant) date).toDateTime().toLocalTime();
		}
		if (date instanceof org.joda.time.DateTime) {
			return ((org.joda.time.DateTime) date).toLocalTime();
		}
		if (date instanceof org.joda.time.LocalDate) {
			return ((org.joda.time.LocalDate) date).toDateTimeAtStartOfDay().toLocalTime(); 
		}
		if (date instanceof LocalTime) {
			return (LocalTime) date;
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			return ((org.joda.time.LocalDateTime) date).toLocalTime(); 
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to org.joda.time.LocalTime");
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
			DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
			return LocalTime.parse(dateString, formatter);
		} catch (Exception e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to org.joda.time.LocalTime", e);
		}
	}

}