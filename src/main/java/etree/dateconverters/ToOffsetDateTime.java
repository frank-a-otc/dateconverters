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

// TODO: Auto-generated Javadoc
class ToOffsetDateTime extends AbstractDateConversions {
	private static final Logger LOGGER = LoggerFactory.getLogger(ToOffsetDateTime.class);

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
			return OffsetDateTime.ofInstant(Instant.ofEpochMilli(localDateTime.toDateTime().getMillis()),
					DEFAULT_ZONE_ID);
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to OffsetDateTime");
	}

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