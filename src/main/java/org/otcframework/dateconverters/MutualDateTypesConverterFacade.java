/**
*
* @author  Franklin Abel (Joshua), 
* @version 1.0
* @since   2020-09-10 
*/
package org.otcframework.dateconverters;

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

import org.otcframework.dateconverters.exception.DateConverterException;

// TODO: Auto-generated Javadoc
/**
 * The Class MutualDateTypesConverterFacade.
 */
public class MutualDateTypesConverterFacade {

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
	private MutualDateTypesConverterFacade() { }
	
	/** The Constant dateConverterFacade. */
	private static final MutualDateTypesConverterFacade dateConverterFacade = new MutualDateTypesConverterFacade();
	
	/**
	 * Gets the single instance of MutualDateTypesConverterFacade.
	 *
	 * @return single instance of MutualDateTypesConverterFacade
	 */
	public static MutualDateTypesConverterFacade getInstance() {
		// no need of string singleton.
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
		throw new DateConverterException("", "Date conversion error! Unable to convert " +
				from.getClass().getName() + " to " + toClz);
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
			return (T) ToInstant.toInstant(strDate, format);
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
		if (Instant.class == toClz && strDate != null) {
			return (T) ToInstant.toInstant(strDate);
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
		throw new DateConverterException("", "Date conversion error! Unable to convert " +
				strDate + " to " + toClz);
	}
}