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
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

import etree.dateconverters.exception.DateConverterException;

// TODO: Auto-generated Javadoc
class ToCalendar extends AbstractDateConversions {
	private static final Logger LOGGER = LoggerFactory.getLogger(ToCalendar.class);

	public static <F> Calendar toCalendar(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof String) {
			return DateParserUtils.parseCalendar((String) date);
		}
		if (date instanceof Date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date) date);
			return calendar;
		}
		if (date instanceof Calendar) { // date instanceof GregorianCalendar ||
			return (Calendar) date;
		}
		if (date instanceof XMLGregorianCalendar) {
			return ((XMLGregorianCalendar) date).toGregorianCalendar();
		}
		if (date instanceof Instant) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(((Instant) date).toEpochMilli());
			return calendar;
		}
		if (date instanceof LocalDate) {
			return GregorianCalendar.from(((LocalDate) date).atStartOfDay(DEFAULT_ZONE_ID));
		}
		if (date instanceof LocalTime) {
			LOGGER.warn("No date information available to convert to Calendar.");
			return null;
		}
		if (date instanceof LocalDateTime) {
			return GregorianCalendar.from(((LocalDateTime) date).atZone(DEFAULT_ZONE_ID));
		}
		if (date instanceof ZonedDateTime) {
			return GregorianCalendar.from(((ZonedDateTime) date));
		}
		if (date instanceof OffsetDateTime) {
			return GregorianCalendar.from(((OffsetDateTime) date).toZonedDateTime());
		}
		if (date instanceof org.joda.time.Instant) {
			return (((org.joda.time.Instant) date).toDateTime().toCalendar(DEFAULT_LOCALE));
		}
		if (date instanceof org.joda.time.DateTime) {
			return ((org.joda.time.DateTime) date).toCalendar(DEFAULT_LOCALE);
		}
		if (date instanceof org.joda.time.LocalDate) {
			return (((org.joda.time.LocalDate) date).toDateTime(org.joda.time.LocalTime.MIDNIGHT))
					.toCalendar(DEFAULT_LOCALE);
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to java.sql.Date. Returning null.");
			return null;
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			return (((org.joda.time.LocalDateTime) date).toDateTime().toCalendar(DEFAULT_LOCALE));
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to Calendar");
	}

	public static Calendar toCalendar(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new SimpleDateFormat(format).parse((String) dateString));
			return calendar;
		} catch (ParseException e) {
			throw new DateConverterException(
					"Calendar conversion error! Unable to convert " + dateString + " to Calendar.", e);
		}
	}
}