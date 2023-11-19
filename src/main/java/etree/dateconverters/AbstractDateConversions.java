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

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;


/**
 * The Class AbstractDateConversions.
 */

class AbstractDateConversions {

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
