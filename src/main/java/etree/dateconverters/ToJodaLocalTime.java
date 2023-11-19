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

import com.github.sisyphsu.dateparser.DateParserUtils;

import etree.dateconverters.exception.DateConverterException;


/**
 * The Class ToJodaLocalTime.
 */

class ToJodaLocalTime extends AbstractDateConversions {

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
			return new LocalTime(date);
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
		throw new DateConverterException("", "Date conversion error! Unable to convert " + date.getClass().getName()
				+ " to org.joda.time.LocalTime");
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