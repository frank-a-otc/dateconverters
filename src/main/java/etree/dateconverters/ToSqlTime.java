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

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import com.github.sisyphsu.dateparser.DateParserUtils;

import etree.dateconverters.exception.DateConverterException;


/**
 * The Class ToSqlTime.
 */

class ToSqlTime extends AbstractDateConversions {

	/**
	 * To sql time.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the time
	 */
	public static <F> Time toSqlTime(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof String) {
			Date utilDate = DateParserUtils.parseDate((String) date);
			return new Time(utilDate.getTime());
		}
		if (date instanceof Date) {
			return new Time(((java.util.Date) date).getTime());
		}
		if (date instanceof Timestamp) {
			return new Time(((Timestamp) date).getTime());
		}
		if (date instanceof Time) {
			return (Time) date;
		}
		if (date instanceof Calendar) {
			return new Time(((Calendar) date).getTimeInMillis());
		}
		if (date instanceof XMLGregorianCalendar) {
			return new Time(((XMLGregorianCalendar) date).toGregorianCalendar().getTimeInMillis());
		}
		if (date instanceof Instant) {
			return new Time(((Instant) date).toEpochMilli());
		}
		if (date instanceof LocalDate) {
			return new Time(((LocalDate) date).atStartOfDay(DEFAULT_ZONE_ID).toInstant().toEpochMilli());
		}
		if (date instanceof LocalTime) {
			return Time.valueOf((LocalTime) date);
		}
		if (date instanceof LocalDateTime) {
			return new Time(((LocalDateTime) date).atZone(DEFAULT_ZONE_ID).toInstant().toEpochMilli());
		}
		if (date instanceof ZonedDateTime) {
			return new Time(((ZonedDateTime) date).toInstant().toEpochMilli());
		}
		if (date instanceof OffsetDateTime) {
			return new Time(((OffsetDateTime) date).toInstant().toEpochMilli());
		}
		if (date instanceof org.joda.time.Instant) {
			return new Time(((org.joda.time.Instant) date).toDate().getTime());
		}
		if (date instanceof org.joda.time.DateTime) {
			return new Time(((org.joda.time.DateTime) date).toDate().getTime());
		}
		if (date instanceof org.joda.time.LocalDate) {
			return new Time(((org.joda.time.LocalDate) date).toDate().getTime());
		}
		if (date instanceof org.joda.time.LocalTime) {
			org.joda.time.LocalTime localTime = (org.joda.time.LocalTime) date;
			return new Time(localTime.getHourOfDay(), localTime.getMinuteOfHour(), localTime.getSecondOfMinute());
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			return new Time(((org.joda.time.LocalDateTime) date).toDate().getTime());
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to java.sql.Time");
	}

	/**
	 * To sql time.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @return the time
	 */
	public static Time toSqlTime(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			Date date = new SimpleDateFormat(format).parse(dateString);
			return new Time(date.getTime());
		} catch (ParseException e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to java.sql.Time", e);
		}
	}
}