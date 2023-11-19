/**
* Copyright (c) otcframework.org
*
* @author  Franklin Abel
* @version 1.0
* @since   2020-06-08 
*
* This file is part of the OTC framework.
* 
*  The OTC framework is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, version 3 of the License.
*
*  The OTC framework is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  A copy of the GNU General Public License is made available as 'License.md' file, 
*  along with OTC framework project.  If not, see <https://www.gnu.org/licenses/>.
*
*/
package etree.dateconverters;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

import etree.dateconverters.exception.DateConverterException;


/**
 * The Class ToLocalDateTime.
 */

class ToLocalDateTime extends AbstractDateConversions {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToLocalDateTime.class);

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
			return Instant.ofEpochMilli(utilDate.getTime()).atZone(DEFAULT_ZONE_ID).toLocalDateTime();
		}
		if (date instanceof Date) {
			return Instant.ofEpochMilli(((Date) date).getTime()).atZone(DEFAULT_ZONE_ID).toLocalDateTime();
		}
		if (date instanceof Timestamp) {
			return ((Timestamp) date).toLocalDateTime();
		}
		if (date instanceof Calendar) {
			Calendar calendar = (Calendar) date;
			TimeZone timeZone = calendar.getTimeZone();
			ZoneId zoneId = timeZone == null ? DEFAULT_ZONE_ID : timeZone.toZoneId();
			return LocalDateTime.ofInstant(calendar.toInstant(), zoneId);
		}
		if (date instanceof XMLGregorianCalendar) {
			return ((XMLGregorianCalendar) date).toGregorianCalendar().toZonedDateTime().toLocalDateTime();
		}
		if (date instanceof Instant) {
			Instant instant = ((Instant) date);
			return instant.atZone(DEFAULT_ZONE_ID).toLocalDateTime();
		}
		if (date instanceof LocalDate) {
			return ((LocalDate) date).atStartOfDay();
		}
		if (date instanceof LocalTime) {
			LOGGER.warn("No date information available to convert to LocalDateTime. Returning null.");
			return null;
		}
		if (date instanceof LocalDateTime) {
			return (LocalDateTime) date;
		}
		if (date instanceof ZonedDateTime) {
			return ((ZonedDateTime) date).toLocalDateTime();
		}
		if (date instanceof OffsetDateTime) {
			return ((OffsetDateTime) date).toZonedDateTime().toLocalDateTime();
		}
		if (date instanceof org.joda.time.Instant) {
			return LocalDateTime.ofInstant(Instant.ofEpochMilli(((org.joda.time.Instant) date).getMillis()),
					DEFAULT_ZONE_ID);
		}
		if (date instanceof org.joda.time.DateTime) {
			return LocalDateTime.ofInstant(Instant.ofEpochMilli(((org.joda.time.DateTime) date).getMillis()),
					DEFAULT_ZONE_ID);
		}
		if (date instanceof org.joda.time.LocalDate) {
			org.joda.time.DateTime dateTime = ((org.joda.time.LocalDate) date).toDateTimeAtStartOfDay();
			return LocalDateTime.ofInstant(Instant.ofEpochMilli(dateTime.getMillis()), DEFAULT_ZONE_ID);
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to Instant. Returning null.");
			return null;
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			org.joda.time.LocalDateTime localDateTime = ((org.joda.time.LocalDateTime) date);
			return LocalDateTime.ofInstant(Instant.ofEpochMilli(localDateTime.toDateTime().getMillis()),
					DEFAULT_ZONE_ID);
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to LocalDateTime");
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
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return LocalDateTime.parse(dateString, formatter);
		} catch (Exception e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to LocalDateTime", e);
		}
	}
}