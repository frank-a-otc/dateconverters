/**
*
* @author  Franklin Abel (Joshua), 
* @version 1.0
* @since   2020-09-10 
*/
package org.otcframework.dateconverters;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.otcframework.dateconverters.exception.DateConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ToInstant.
 */
class ToInstant extends AbstractDateConversions {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToInstant.class);

	/**
	 * To instant.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the instant
	 */
	public static <F> Instant toInstant(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof String) {
			Date utilDate = DateParserUtils.parseDate((String) date);
			return utilDate.toInstant();
		} 
		if (date instanceof Date) {
			return Instant.ofEpochMilli(((Date) date).getTime());
		}
		if (date instanceof Calendar) {
			Calendar calendar = (Calendar) date;
			return calendar.toInstant();
		}
		if (date instanceof XMLGregorianCalendar) {
			return ((XMLGregorianCalendar) date).toGregorianCalendar().toInstant();
		}
		if (date instanceof Instant) {
			return (Instant) date;
		}
		if (date instanceof LocalDate) {
			return ((LocalDate) date).atStartOfDay().toInstant(DEFAULT_ZONE_OFFSET);
		}
		if (date instanceof LocalTime) {
			LOGGER.warn("No date information available to convert to Instant. Returning null.");
			return null;
		}
		if (date instanceof LocalDateTime) {
			return ((LocalDateTime) date).toInstant(DEFAULT_ZONE_OFFSET);
		}
		if (date instanceof ZonedDateTime) {
			return ((ZonedDateTime) date).toInstant();
		}
		if (date instanceof OffsetDateTime) {
			return ((OffsetDateTime) date).toInstant();
		}
		if (date instanceof org.joda.time.Instant) {
			return Instant.ofEpochMilli(((org.joda.time.Instant) date).getMillis());
		}
		if (date instanceof org.joda.time.DateTime) {
			return Instant.ofEpochMilli(((org.joda.time.DateTime) date).getMillis());
		}
		if (date instanceof org.joda.time.LocalDate) {
			return Instant.ofEpochMilli(((org.joda.time.LocalDate) date).toDate().getTime());
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to Instant. Returning null.");
			return null;
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			return Instant.ofEpochMilli(((org.joda.time.LocalDateTime) date).toDate().getTime()); 
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to java.time.Instant!");
	}

	/**
	 * To instant.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @return the instant
	 */
	public static Instant toInstant(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			LOGGER.warn("Ignoring date-format as it is not applicable while converting to java.time.Instant!");
			return Instant.parse(dateString);
		} catch (Exception e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to java.time.Instant!", e);
		}
	}

}