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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sisyphsu.dateparser.DateParserUtils;

import etree.dateconverters.exception.DateConverterException;


/**
 * The Class ToXMLGregorianCalendar.
 */

class ToXMLGregorianCalendar extends AbstractDateConversions {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ToXMLGregorianCalendar.class);

	/**
	 * To XML gregorian calendar.
	 *
	 * @param <F> the generic type
	 * @param date the date
	 * @return the XML gregorian calendar
	 */
	public static <F> XMLGregorianCalendar toXMLGregorianCalendar(F date) {
		if (date == null) {
			return null;
		}
		try {
			GregorianCalendar gc = null;
			if (date instanceof String) {
				Date utlDate = DateParserUtils.parseDate((String) date);
				gc = new GregorianCalendar();
				gc.setTime(utlDate);
			}
			if (date instanceof Date) {
				gc = new GregorianCalendar();
				gc.setTime((Date) date);
			}
			if (date instanceof Calendar) {
				Date utlDate = ((Calendar) date).getTime();
				gc = new GregorianCalendar();
				gc.setTime(utlDate);
			}
			if (date instanceof XMLGregorianCalendar) {
				return ((XMLGregorianCalendar) date);
			}
			if (date instanceof Instant) {
				gc = new GregorianCalendar();
				Instant instant = (Instant) date;
				gc.setTimeInMillis(instant.toEpochMilli());
			}
			if (date instanceof LocalDate) {
				gc = GregorianCalendar.from(((LocalDate) date).atStartOfDay(DEFAULT_ZONE_ID));
			}
			if (date instanceof LocalTime) {
				LOGGER.warn("No date information available to convert to XMLGregorianCalendar. Returning null.");
				return null;
			}
			if (date instanceof LocalDateTime) {
				gc = GregorianCalendar.from(((LocalDateTime) date).atZone(DEFAULT_ZONE_ID));
			}
			if (date instanceof ZonedDateTime) {
				gc = GregorianCalendar.from(((ZonedDateTime) date));
			}
			if (date instanceof OffsetDateTime) {
				gc = GregorianCalendar.from(((OffsetDateTime) date).toZonedDateTime());
			}
			if (date instanceof org.joda.time.Instant) {
				gc = new GregorianCalendar();
				gc.setTime(((org.joda.time.Instant) date).toDate());
			}
			if (date instanceof org.joda.time.DateTime) {
				gc = new GregorianCalendar();
				gc.setTime(((org.joda.time.DateTime) date).toDate());
			}
			if (date instanceof org.joda.time.LocalDate) {
				gc = new GregorianCalendar();
				gc.setTime(((org.joda.time.LocalDate) date).toDate());
			}
			if (date instanceof org.joda.time.LocalTime) {
				LOGGER.warn("No date information available to convert to XMLGregorianCalendar. Returning null.");
				return null;
			}
			if (date instanceof org.joda.time.LocalDateTime) {
				gc = new GregorianCalendar();
				gc.setTime(((org.joda.time.LocalDateTime) date).toDate());
			}
			if (gc != null) {
				return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			}
		} catch (DatatypeConfigurationException e) {
			throw new DateConverterException("", "Date conversion error! Unable to convert " + date.getClass().getName()
					+ " to XMLGregorianCalendar", e);
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + date.getClass().getName() + " to XMLGregorianCalendar");
	}

	/**
	 * To XML gregorian calendar.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @return the XML gregorian calendar
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		try {
			GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
			gc.setTime(new SimpleDateFormat(format).parse(dateString));
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (ParseException | DatatypeConfigurationException e) {
			throw new DateConverterException(
					"Calendar conversion error! Unable to convert " + dateString + " to XMLGregorianCalendar.", e);
		}
	}
}