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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

import etree.dateconverters.exception.DateConverterException;


/**
 * The Class ToJodaInstant.
 */

class ToJodaInstant extends AbstractDateConversions {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToJodaInstant.class);

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
			return Instant.ofEpochMilli(DateParserUtils.parseDate((String) date).getTime());
		}
		if (date instanceof Date) {
			return Instant.ofEpochMilli(((Date) date).getTime());
		}
		if (date instanceof java.sql.Date) {
			return Instant.ofEpochMilli(((java.sql.Date) date).getTime());
		}
		if (date instanceof Timestamp) {
			return Instant.ofEpochMilli(((Timestamp) date).toInstant().toEpochMilli());
		}
		if (date instanceof Calendar) {
			Calendar calendar = (Calendar) date;
			return Instant.ofEpochMilli(calendar.toInstant().toEpochMilli());
		}
		if (date instanceof XMLGregorianCalendar) {
			return Instant.ofEpochMilli(((XMLGregorianCalendar) date).toGregorianCalendar().toInstant().toEpochMilli());
		}
		if (date instanceof java.time.Instant) {
			return new Instant(((java.time.Instant) date).toEpochMilli());
		}
		if (date instanceof LocalDate) {
			return Instant.ofEpochMilli(((LocalDate) date).atStartOfDay(DEFAULT_ZONE_ID).toInstant().toEpochMilli());
		}
		if (date instanceof LocalTime) {
			LOGGER.warn("No date information available to convert to org.joda.time.Instant. Returning null.");
			return null;
		}
		if (date instanceof LocalDateTime) {
			return Instant.ofEpochMilli(((LocalDateTime) date).toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli());
		}
		if (date instanceof ZonedDateTime) {
			return Instant.ofEpochMilli(((ZonedDateTime) date).toInstant().toEpochMilli());
		}
		if (date instanceof OffsetDateTime) {
			return Instant.ofEpochMilli(((OffsetDateTime) date).toInstant().toEpochMilli());
		}
		if (date instanceof Instant) {
			return (Instant) date;
		}
		if (date instanceof org.joda.time.DateTime) {
			return ((org.joda.time.DateTime) date).toInstant();
		}
		if (date instanceof org.joda.time.LocalDate) {
			return Instant.ofEpochMilli(((org.joda.time.LocalDate) date).toDate().toInstant().toEpochMilli());
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to org.joda.time.Instant. Returning null.");
			return null;
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			return Instant.ofEpochMilli(((org.joda.time.LocalDateTime) date).toDate().toInstant().toEpochMilli());
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to org.joda.time.Instant");
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
			DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
			return Instant.parse(dateString, formatter);
		} catch (Exception e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to org.joda.time.Instant", e);
		}
	}
}