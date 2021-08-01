/**
*
* @author  Franklin Abel (Joshua), 
* @version 1.0
* @since   2020-09-10 
*/
package org.otcframework.dateconverters;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.otcframework.dateconverters.exception.DateConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ToJodaLocalDateTime.
 */
class ToJodaLocalDateTime extends AbstractDateConversions {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToJodaLocalDateTime.class);

	/**
	 * To local date time.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the local date time
	 */
	public static <F> LocalDateTime toLocalDateTime(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof String) {
			Date utilDate = DateParserUtils.parseDate((String) date);
			return new LocalDateTime(utilDate);
		} 
		if (date instanceof Date) {
			return new LocalDateTime((Date) date);
		}
		if (date instanceof Calendar) {
			return new LocalDateTime((Calendar) date);
		}
		if (date instanceof XMLGregorianCalendar) {
			return new LocalDateTime(((XMLGregorianCalendar) date).toGregorianCalendar());
		}
		if (date instanceof java.time.Instant) {
			return new LocalDateTime(((java.time.Instant) date).toEpochMilli());
		}
		if (date instanceof Instant) {
			Instant instant = ((Instant) date);
			return new LocalDateTime(instant);
		}
		if (date instanceof LocalDate) {
			return new LocalDateTime(((LocalDate) date).atStartOfDay(DEFAULT_ZONE_ID).toInstant().toEpochMilli());
		}
		if (date instanceof LocalTime) {
			LOGGER.warn("No date information available to convert to org.joda.time.LocalDateTime. Returning null.");
			return null;
		}
		if (date instanceof java.time.LocalDateTime) {
			return new LocalDateTime(((java.time.LocalDateTime) date).atZone(DEFAULT_ZONE_ID).toInstant().toEpochMilli());
		}
		if (date instanceof ZonedDateTime) {
			return new LocalDateTime(((ZonedDateTime) date).toInstant().toEpochMilli());
		}
		if (date instanceof OffsetDateTime) {
			return new LocalDateTime(((OffsetDateTime) date).toInstant().toEpochMilli());
		}
		if (date instanceof org.joda.time.Instant) {
			return ((org.joda.time.Instant) date).toDateTime().toLocalDateTime();
		}
		if (date instanceof org.joda.time.DateTime) {
			return ((org.joda.time.DateTime) date).toLocalDateTime();
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to org.joda.time.LocalDateTime. Returning null.");
			return null;
		}
		if (date instanceof org.joda.time.LocalDate) {
			return ((org.joda.time.LocalDate) date).toDateTimeAtStartOfDay().toLocalDateTime(); 
		}
		if (date instanceof LocalDateTime) {
			return (LocalDateTime) date;
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to org.joda.time.LocalDateTime");
	}

	/**
	 * To local date time.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @return the local date time
	 */
	public static LocalDateTime toLocalDateTime(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
			return LocalDateTime.parse(dateString, formatter);
		} catch (Exception e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to org.joda.time.LocalDateTime", e);
		}
	}

}