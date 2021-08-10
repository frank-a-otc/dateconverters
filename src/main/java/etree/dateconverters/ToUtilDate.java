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
/**
 * The Class ToUtilDate.
 */
// TODO: Auto-generated Javadoc
class ToUtilDate extends AbstractDateConversions {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToUtilDate.class);

	/**
	 * To util date.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the date
	 */
	public static <F> Date toUtilDate(F date) {
		if (date == null) {
			return null;
		}
		if (date instanceof Date) {
			return (Date) date;
		}
		if (date instanceof String) {
			return DateParserUtils.parseDate((String) date);
		}
		if (date instanceof Calendar) {
			return ((Calendar) date).getTime();
		}
		if (date instanceof java.sql.Timestamp) {
			return new Date(((Date) date).getTime());
		}
		if (date instanceof GregorianCalendar) {
			return ((GregorianCalendar) date).getTime();
		}
		if (date instanceof XMLGregorianCalendar) {
			return ((XMLGregorianCalendar) date).toGregorianCalendar().getTime();
		}
		if (date instanceof Instant) {
			return Date.from((Instant) date);
		}
		if (date instanceof LocalDate) {
			return Date.from(((LocalDate) date).atStartOfDay(DEFAULT_ZONE_ID).toInstant());
		}
		if (date instanceof LocalTime) {
			LOGGER.warn("No date information available to convert to Date. Returning null.");
			return null;
		}
		if (date instanceof LocalDateTime) {
			return Date.from(((LocalDateTime) date).atZone(DEFAULT_ZONE_ID).toInstant());
		}
		if (date instanceof ZonedDateTime) {
			return Date.from(((ZonedDateTime) date).toInstant());
		}
		if (date instanceof OffsetDateTime) {
			return Date.from(((OffsetDateTime) date).toInstant());
		}
		if (date instanceof org.joda.time.Instant) {
			return ((org.joda.time.Instant) date).toDateTime().toDate();
		}
		if (date instanceof org.joda.time.DateTime) {
			return ((org.joda.time.DateTime) date).toDate();
		}
		if (date instanceof org.joda.time.LocalDate) {
			return ((org.joda.time.LocalDate) date).toDate();
		}
		if (date instanceof org.joda.time.LocalTime) {
			LOGGER.warn("No date information available to convert to Date. Returning null.");
			return null;
		}
		if (date instanceof org.joda.time.LocalDateTime) {
			return ((org.joda.time.LocalDateTime) date).toDate();
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to java.util.Date");
	}

	/**
	 * To util date.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @return the date
	 */
	public static Date toUtilDate(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(format).parse((String) dateString);
		} catch (ParseException e) {
			throw new DateConverterException("",
					"Date conversion error! Unable to convert " + dateString + " to java.util.Date", e);
		}
	}
}