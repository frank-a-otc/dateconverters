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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import etree.dateconverters.exception.DateConverterException;



/**
 * The Class DateConverterFacade.
 */
public class DateConverterFacade {
	
	/** The Constant dateTypes. */
	private static final Set<Class<?>> dateTypes = new HashSet<>(15);
	
	static {
		dateTypes.add(Date.class);
		dateTypes.add(java.sql.Date.class);
		dateTypes.add(Timestamp.class);
		dateTypes.add(Time.class);
		dateTypes.add(Calendar.class);
		dateTypes.add(XMLGregorianCalendar.class);
		dateTypes.add(Instant.class);
		dateTypes.add(LocalDate.class);
		dateTypes.add(LocalTime.class);
		dateTypes.add(LocalDateTime.class);
		dateTypes.add(ZonedDateTime.class);
		dateTypes.add(OffsetDateTime.class);
		dateTypes.add(org.joda.time.Instant.class);
		dateTypes.add(org.joda.time.LocalDate.class);
		dateTypes.add(org.joda.time.LocalDateTime.class);
		dateTypes.add(org.joda.time.LocalTime.class);
	}

	/**
	 * Instantiates a new mutual date types converter facade.
	 */
	private DateConverterFacade() {
	}

	/** The Constant dateConverterFacade. */
	private static final DateConverterFacade dateConverterFacade = new DateConverterFacade();

	/**
	 * Gets the single instance of DateConverterFacade.
	 *
	 * @return single instance of DateConverterFacade
	 */
	public static DateConverterFacade getInstance() {
		// no need of strict singleton.
		return dateConverterFacade;
	}

	/**
	 * Checks if is of any date type.
	 *
	 * @param clz the clz
	 * @return true, if is of any date type
	 */
	public static boolean isOfAnyDateType(Class<?> clz) {
		return dateTypes.contains(clz);
	}

	/**
	 * Convert.
	 *
	 * @param <F> the generic type
	 * @param <T> the generic type
	 * @param from the from
	 * @param toClz the to clz
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public static <F, T> T convert(F from, Class<? extends T> toClz) {
		if (from == null) {
			return null;
		}
		if (Date.class == toClz) {
			return (T) ToUtilDate.toUtilDate(from);
		}
		if (java.sql.Date.class == toClz) {
			return (T) ToSqlDate.toSqlDate(from);
		}
		if (Time.class == toClz) {
			return (T) ToSqlTime.toSqlTime(from);
		}
		if (Timestamp.class == toClz) {
			return (T) ToSqlTimestamp.toSqlTimestamp(from);
		}
		if (Calendar.class == toClz || GregorianCalendar.class == toClz) {
			return (T) ToCalendar.toCalendar(from);
		}
		if (Instant.class == toClz) {
			return (T) ToInstant.toInstant(from);
		}
		if (LocalDate.class == toClz) {
			return (T) ToLocalDate.toLocalDate(from);
		}
		if (LocalTime.class == toClz) {
			return (T) ToLocalTime.toLocalTime(from);
		}
		if (LocalDateTime.class == toClz) {
			return (T) ToLocalDateTime.toLocalDateTime(from);
		}
		if (ZonedDateTime.class == toClz) {
			return (T) ToZonedDateTime.toZonedDateTime(from);
		}
		if (OffsetDateTime.class == toClz) {
			return (T) ToOffsetDateTime.toOffsetDateTime(from);
		}
		if (XMLGregorianCalendar.class == toClz) {
			return (T) ToXMLGregorianCalendar.toXMLGregorianCalendar(from);
		}
		if (org.joda.time.Instant.class == toClz) {
			return (T) ToJodaInstant.toInstant(from);
		}
		if (org.joda.time.LocalDate.class == toClz) {
			return (T) ToJodaLocalDate.toLocalDate(from);
		}
		if (org.joda.time.LocalDateTime.class == toClz) {
			return (T) ToJodaLocalDateTime.toLocalDateTime(from);
		}
		if (org.joda.time.LocalTime.class == toClz) {
			return (T) ToJodaLocalTime.toLocalTime(from);
		}
		throw new DateConverterException("",
				"Date conversion error! Unable to convert " + from.getClass().getName() + " to " + toClz);
	}

	/**
	 * Convert.
	 *
	 * @param <T> the generic type
	 * @param strDate the str date
	 * @param toClz the to clz
	 * @param format the format
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convert(String strDate, Class<? extends T> toClz, String format) {
		if (strDate == null) {
			return null;
		}
		assert dateTypes.contains(toClz) : "Connection is null";
		if (Date.class == toClz) {
			return (T) ToUtilDate.toUtilDate(strDate, format);
		}
		if (java.sql.Date.class == toClz) {
			return (T) ToSqlDate.toSqlDate(strDate, format);
		}
		if (Time.class == toClz) {
			return (T) ToSqlTime.toSqlTime(strDate, format);
		}
		if (Timestamp.class == toClz) {
			return (T) ToSqlTimestamp.toSqlTimestamp(strDate, format);
		}
		if (Calendar.class == toClz) {
			return (T) ToCalendar.toCalendar(strDate, format);
		}
		if (Instant.class == toClz) {
			return (T) ToInstant.toInstant(strDate);
		}
		if (LocalDate.class == toClz) {
			return (T) ToLocalDate.toLocalDate(strDate, format);
		}
		if (LocalTime.class == toClz) {
			return (T) ToLocalTime.toLocalTime(strDate, format);
		}
		if (LocalDateTime.class == toClz) {
			return (T) ToLocalDateTime.toLocalDateTime(strDate, format);
		}
		if (ZonedDateTime.class == toClz) {
			return (T) ToZonedDateTime.toZonedDateTime(strDate, format);
		}
		if (OffsetDateTime.class == toClz) {
			return (T) ToOffsetDateTime.toOffsetDateTime(strDate, format);
		}
		if (XMLGregorianCalendar.class == toClz) {
			return (T) ToXMLGregorianCalendar.toXMLGregorianCalendar(strDate, format);
		}
		if (org.joda.time.Instant.class == toClz) {
			return (T) ToJodaInstant.toInstant(strDate, format);
		}
		if (org.joda.time.LocalDate.class == toClz) {
			return (T) ToJodaLocalDate.toLocalDate(strDate, format);
		}
		if (org.joda.time.LocalDateTime.class == toClz) {
			return (T) ToJodaLocalDateTime.toLocalDateTime(strDate, format);
		}
		if (org.joda.time.LocalTime.class == toClz) {
			return (T) ToJodaLocalTime.toLocalTime(strDate, format);
		}
		throw new DateConverterException("", "Date conversion error! Unable to convert " + strDate + " to " + toClz);
	}
}