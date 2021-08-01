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
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.otcframework.dateconverters.exception.DateConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ToJodaDateTime.
 */
class ToJodaDateTime extends AbstractDateConversions {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToJodaDateTime.class);

	/**
	 * To date time.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the date time
	 */
	public static <F> DateTime toDateTime(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof String) {
			Date utilDate = DateParserUtils.parseDate((String) date);
			return new DateTime(utilDate);
		} 
		if (date instanceof Date || date instanceof java.sql.Date) {
			return new DateTime((Date) date);
		}
		if (date instanceof Timestamp) {
			return new DateTime((Timestamp) date);
		}
		if (date instanceof Calendar) {
			return new DateTime((Calendar) date);
		}
		if (date instanceof XMLGregorianCalendar) {
			return new DateTime(((XMLGregorianCalendar) date).toGregorianCalendar());
		}
		if (date instanceof Instant) {
			return new DateTime(((Instant) date));
		}
		if (date instanceof LocalDate) {
			return new DateTime(((LocalDate) date));
		}
		if (date instanceof ZonedDateTime) {
			return new DateTime((ZonedDateTime) date);
		}
		if (date instanceof OffsetDateTime) {
			return new DateTime((OffsetDateTime) date);
		}
		if (date instanceof org.joda.time.Instant) {
			return ((org.joda.time.Instant) date).toDateTime();
		}
		if (date instanceof DateTime) {
			return (DateTime) date;
		}
		if (date instanceof org.joda.time.LocalDate) {
			return ((org.joda.time.LocalDate) date).toDateTimeAtStartOfDay();
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to org.joda.time.DateTime.");
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			return ((org.joda.time.LocalDateTime) date).toDateTime();
		}
		throw new DateConverterException("", "Date conversion error! Unable to convert " + date.getClass().getName() +
				" to org.joda.time.DateTime");
	}

	/**
	 * To local date time.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @return the org.joda.time. local date time
	 */
	public static org.joda.time.LocalDateTime toLocalDateTime(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
			return org.joda.time.LocalDateTime.parse(dateString, formatter);
		} catch (Exception e) {
			throw new DateConverterException("", "Date conversion error! Unable to convert " + dateString + 
					" to org.joda.time.DateTime", e);
		}
	}

}