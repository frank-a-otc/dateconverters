/**
*
* @author  Franklin Abel (Joshua), 
* @version 1.0
* @since   2020-09-10 
*/
package org.otcl.dateconverters;

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

import org.otcl.dateconverters.exception.DateConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ToOffsetDateTime.
 */
class ToOffsetDateTime extends AbstractDateConversions {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToOffsetDateTime.class);

	/**
	 * To offset date time.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the offset date time
	 */
	public static <F> OffsetDateTime toOffsetDateTime(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof String) {
			Date utilDate = DateParserUtils.parseDate((String) date);
			return utilDate.toInstant().atOffset(DEFAULT_ZONE_OFFSET);
		}
		if (date instanceof Date) {
			return Instant.ofEpochMilli(((Date) date).getTime()).atZone(DEFAULT_ZONE_ID).toOffsetDateTime();
		}
		if (date instanceof Timestamp) {
			Timestamp timestamp = (Timestamp) date;
			return OffsetDateTime.ofInstant(timestamp.toInstant(), DEFAULT_ZONE_OFFSET);
		}
		if (date instanceof Calendar) {
			Calendar calendar = (Calendar) date;
			TimeZone timeZone = calendar.getTimeZone();
			ZoneId zoneId = timeZone == null ? DEFAULT_ZONE_ID : timeZone.toZoneId();
			return OffsetDateTime.ofInstant(calendar.toInstant(), zoneId);
		}
		if (date instanceof XMLGregorianCalendar) {
			return OffsetDateTime.from(((XMLGregorianCalendar) date).toGregorianCalendar().toZonedDateTime());
		}
		if (date instanceof Instant) {
			Instant instant = ((Instant) date);
			return instant.atZone(DEFAULT_ZONE_ID).toOffsetDateTime();
		}
		if (date instanceof LocalDate) {
			return ((LocalDate) date).atStartOfDay().atOffset(DEFAULT_ZONE_OFFSET);
		}
		if (date instanceof LocalTime) {
			LOGGER.warn("No date information available to convert to OffsetDateTime. Returning null.");
			return null;
		}
		if (date instanceof LocalDateTime) {
			return ((LocalDateTime) date).atOffset(DEFAULT_ZONE_OFFSET);
		}
		if (date instanceof ZonedDateTime) {
			return ((ZonedDateTime) date).toOffsetDateTime();
		}
		if (date instanceof OffsetDateTime) {
			return (OffsetDateTime) date;
		}
		if (date instanceof org.joda.time.Instant) {
			return OffsetDateTime.ofInstant(Instant.ofEpochMilli(((org.joda.time.Instant) date).getMillis()),
					DEFAULT_ZONE_ID);
		}
		if (date instanceof org.joda.time.DateTime) {
			return OffsetDateTime.ofInstant(Instant.ofEpochMilli(((org.joda.time.DateTime) date).getMillis()),
					DEFAULT_ZONE_ID);
		}
		if (date instanceof org.joda.time.LocalDate) {
			org.joda.time.DateTime dateTime = ((org.joda.time.LocalDate) date).toDateTimeAtStartOfDay();
			return OffsetDateTime.ofInstant(Instant.ofEpochMilli(dateTime.getMillis()), DEFAULT_ZONE_ID);
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to OffsetDateTime. Returning null.");
			return null;
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			org.joda.time.LocalDateTime localDateTime = ((org.joda.time.LocalDateTime) date);
			return OffsetDateTime.ofInstant(Instant.ofEpochMilli(localDateTime.toDateTime().getMillis()), DEFAULT_ZONE_ID);
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to OffsetDateTime");
	}

	/**
	 * To offset date time.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @return the offset date time
	 */
	public static OffsetDateTime toOffsetDateTime(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return OffsetDateTime.parse(dateString, formatter);
		} catch (Exception e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to OffsetDateTime", e);
		}
	}

}