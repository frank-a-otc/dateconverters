/**
*
* @author  Franklin Abel (Joshua), 
* @version 1.0
* @since   2020-09-10 
*/
package org.otcl.dateconverters;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractDateConversions.
 */
class AbstractDateConversions {

//	protected static TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");
//	protected static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");
//	protected static ZoneOffset UTC_ZONE_OFFSET = ZoneOffset.UTC;
//	protected static final DateTimeZone UTC_JODA_DATE_TIME_ZONE = DateTimeZone.forID("UTC");

	/** The Constant DEFAULT_LOCALE. */
protected static final Locale DEFAULT_LOCALE = Locale.getDefault();
	
	/** The Constant DEFAULT_TIME_ZONE. */
	protected static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();
	
	/** The Constant DEFAULT_ZONE_ID. */
	protected static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();
	
	/** The Constant DEFAULT_ZONE_OFFSET. */
	protected static final ZoneOffset DEFAULT_ZONE_OFFSET = OffsetDateTime.now().getOffset();
	
	/** The Constant DEFAULT_JODA_DATE_TIME_ZONE. */
	protected static final DateTimeZone DEFAULT_JODA_DATE_TIME_ZONE = DateTimeZone.forTimeZone(DEFAULT_TIME_ZONE);

}
