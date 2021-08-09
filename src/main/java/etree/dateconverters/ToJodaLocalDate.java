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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

import etree.dateconverters.exception.DateConverterException;

// TODO: Auto-generated Javadoc
class ToJodaLocalDate extends AbstractDateConversions {
	private static final Logger LOGGER = LoggerFactory.getLogger(ToJodaLocalDate.class);

	public static <F> LocalDate toLocalDate(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof String) {
			Date utilDate = DateParserUtils.parseDate((String) date);
			return new LocalDate(utilDate);
		}
		if (date instanceof Date) {
			return new LocalDate((Date) date);
		}
		if (date instanceof Timestamp) {
			return new LocalDate((Timestamp) date);
		}
		if (date instanceof Calendar) {
			return new LocalDate((Calendar) date);
		}
		if (date instanceof XMLGregorianCalendar) {
			return new LocalDate(((XMLGregorianCalendar) date).toGregorianCalendar());
		}
		if (date instanceof Instant) {
			return new LocalDate(((Instant) date).toEpochMilli());
		}
		if (date instanceof java.time.LocalDate) {
			return new LocalDate(((java.time.LocalDate) date).atStartOfDay(DEFAULT_ZONE_ID).toInstant().toEpochMilli());
		}
		if (date instanceof LocalTime) {
			LOGGER.warn("No date information available to convert to org.joda.time.LocalDate. Returning null.");
			return null;
		}
		if (date instanceof LocalDateTime) {
			return new LocalDate(((LocalDateTime) date).toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli());
		}
		if (date instanceof ZonedDateTime) {
			return new LocalDate(((ZonedDateTime) date).toInstant().toEpochMilli());
		}
		if (date instanceof OffsetDateTime) {
			return new LocalDate(((OffsetDateTime) date).toInstant().toEpochMilli());
		}
		if (date instanceof org.joda.time.Instant) {
			return ((org.joda.time.Instant) date).toDateTime().toLocalDate();
		}
		if (date instanceof org.joda.time.DateTime) {
			return ((org.joda.time.DateTime) date).toLocalDate();
		}
		if (date instanceof LocalDate) {
			return (LocalDate) date;
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to org.joda.time.LocalDate. Returning null.");
			return null;
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			return ((org.joda.time.LocalDateTime) date).toLocalDate();
		}
		throw new DateConverterException("", "Date conversion error! Unable to convert " + date.getClass().getName()
				+ " to org.joda.time.LocalDate");
	}

	public static LocalDate toLocalDate(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
			return LocalDate.parse(dateString, formatter);
		} catch (Exception e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to org.joda.time.LocalDate", e);
		}
	}
}