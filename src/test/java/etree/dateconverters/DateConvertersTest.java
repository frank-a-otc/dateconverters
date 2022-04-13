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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.xml.datatype.XMLGregorianCalendar;

import etree.dateconverters.DateConverterFacade;
public class DateConvertersTest {

	private static final Set<Class<?>> dateTypes = new LinkedHashSet<>(15);
	private static final Map<String, String> mapDateStr = new LinkedHashMap<>();
	static {
		mapDateStr.put("dateStr0","2009-05-08 17:57:51 +0000");
		mapDateStr.put("dateStr0_1","May 8, 2009 5:57:51 PM");
		mapDateStr.put("dateStr1","1970-10-07 00:00:00 +0000");
		mapDateStr.put("dateStr1_1","7 oct 1970");
		mapDateStr.put("dateStr2","2006-01-02 15:04:05 +0000");
		mapDateStr.put("dateStr2_1","2006-01-02T15:04:05+0000");
		mapDateStr.put("dateStr3","2006-01-02 15:04:05 -0700");
		mapDateStr.put("dateStr3_1","Mon, 02 Jan 2006 15:04:05 -0700");
		mapDateStr.put("dateStr4","2017-07-11 16:28:13 +0200");
		mapDateStr.put("dateStr4_1","Tue, 11 Jul 2017 16:28:13 +0200 (CEST)");
		mapDateStr.put("dateStr5","2018-01-04 17:53:36 +0000");
		mapDateStr.put("dateStr5_1","Thu, 4 Jan 2018 17:53:36 +0000");
		mapDateStr.put("dateStr6","2015-08-10 15:44:11 +0100");
		mapDateStr.put("dateStr6_1","Mon Aug 10 15:44:11 UTC+0100 2015");
		mapDateStr.put("dateStr7","2015-07-03 18:04:07 +0100");
		mapDateStr.put("dateStr7_1","Fri Jul 03 2015 18:04:07 GMT+0100 (GMT Daylight Time)");
		mapDateStr.put("dateStr8","2012-09-17 10:09:00 +0000");
		mapDateStr.put("dateStr8_1","September 17, 2012 10:09am");
		mapDateStr.put("dateStr9","2012-09-17 10:09:00 -0800");
		mapDateStr.put("dateStr9_1","September 17, 2012 at 10:09am PST-08");
		mapDateStr.put("dateStr10","2012-09-17 10:10:09 +0000");
		mapDateStr.put("dateStr10_1","September 17, 2012, 10:10:09");
		mapDateStr.put("dateStr11","2006-02-12 19:17:00 +0000");
		mapDateStr.put("dateStr11_1","12 Feb 2006 19:17");
		mapDateStr.put("dateStr12","2013-02-03 00:00:00 +0000");
		mapDateStr.put("dateStr12_1","2013-Feb-03");
		mapDateStr.put("dateStr13","2013-07-01 00:00:00 +0000");
		mapDateStr.put("dateStr13_1","1 July 2013");
		mapDateStr.put("dateStr14","2014-03-31 00:00:00 +0000");
		mapDateStr.put("dateStr14_1","03.31.2014");
		mapDateStr.put("dateStr15","1971-08-21 00:00:00 +0000");
		mapDateStr.put("dateStr15_1","08.21.71");
		mapDateStr.put("dateStr16","1971-08-01 00:00:00 +0000");
		mapDateStr.put("dateStr16_1","8/1/71");
		mapDateStr.put("dateStr17","2014-04-08 22:05:00 +0000");
		mapDateStr.put("dateStr17_1","2014/04/08 22:05");
		mapDateStr.put("dateStr18","2014-04-02 03:00:51 +0000");
		mapDateStr.put("dateStr18_1","2014/4/02 03:00:51");
		mapDateStr.put("dateStr19","1965-08-08 00:00:00 +0000");
		mapDateStr.put("dateStr19_1","8/8/1965 12:00 AM");
		mapDateStr.put("dateStr20","1965-08-08 13:00:01 +0000");
		mapDateStr.put("dateStr20_1","8/8/1965 01:00:01 PM");
		mapDateStr.put("dateStr21","1965-08-08 13:00:00 +0000");
		mapDateStr.put("dateStr21_1","8/8/1965 1:00 PM");
		mapDateStr.put("dateStr22","2012-03-19 10:11:59 +0000");
		mapDateStr.put("dateStr22_1","1332151919");
		mapDateStr.put("dateStr23","2012-03-19 10:11:59.318636 +0000");
		mapDateStr.put("dateStr23_1","2012/03/19 10:11:59.318636");
		mapDateStr.put("dateStr24","2014-04-08 00:00:00 +0000");
		mapDateStr.put("dateStr24_1","2014年04月08日");
		mapDateStr.put("dateStr25","2009-08-12 22:15:09 -0700");
		mapDateStr.put("dateStr25_1","2009-08-12T22:15:09-07:00");
		mapDateStr.put("dateStr26","2009-08-12 22:15:09 +0000");
		mapDateStr.put("dateStr26_1","2009-08-12T22:15:09Z");
		mapDateStr.put("dateStr27","2014-04-26 17:24:37.318636 +0000");
		mapDateStr.put("dateStr27_1","2014-04-26 17:24:37.318636");
		mapDateStr.put("dateStr28","2012-08-03 18:31:59.257 +0000");
		mapDateStr.put("dateStr28_1","2012-08-03 18:31:59.257000000 +0000 UTC");
		mapDateStr.put("dateStr29","2014-04-26 17:24:37.123 +0000");
		mapDateStr.put("dateStr29_1","2014-04-26 17:24:37.123");
		mapDateStr.put("dateStr30","2013-04-01 22:43:00 +0000");
		mapDateStr.put("dateStr30_1","2013-04-01 22:43");
		mapDateStr.put("dateStr31","2013-04-01 22:43:22 +0000");
		mapDateStr.put("dateStr31_1","2013-04-01 22:43:22");
		mapDateStr.put("dateStr32","2014-12-16 06:20:00 +0000");
		mapDateStr.put("dateStr32_1","2014-12-16 06:20:00 GMT");
		mapDateStr.put("dateStr33","2014-04-26 17:24:37 +0000");
		mapDateStr.put("dateStr33_1","2014-04-26 05:24:37 PM");
		mapDateStr.put("dateStr34","2014-04-26 13:13:43 +0800");
		mapDateStr.put("dateStr34_1","2014-04-26 13:13:43 +0800 +08");
		mapDateStr.put("dateStr35","2014-04-26 13:13:44 +0900");
		mapDateStr.put("dateStr35_1","2014-04-26 13:13:44 +09:00");
		mapDateStr.put("dateStr36","2015-09-30 18:48:56.35272715 +0000");
		mapDateStr.put("dateStr36_1","2015-09-30 18:48:56.35272715 +0000 UTC");
		mapDateStr.put("dateStr37","2015-02-18 00:12:00 +0000");
		mapDateStr.put("dateStr37_1","2015-02-18 00:12:00 +0000 UTC");
		mapDateStr.put("dateStr38","2015-02-08 03:02:00 +0300");
		mapDateStr.put("dateStr38_1","2015-02-08 03:02:00 +0300 MSK m=+0.000000001");
		mapDateStr.put("dateStr39","2015-02-08 03:02:00.001 +0300");
		mapDateStr.put("dateStr39_1","2015-02-08 03:02:00.001 +0300 MSK m=+0.000000001");
		mapDateStr.put("dateStr40","2017-07-19 03:21:51 +0000");
		mapDateStr.put("dateStr40_1","2017-07-19 03:21:51+00:00");
		mapDateStr.put("dateStr41","2014-04-26 00:00:00 +0000");
		mapDateStr.put("dateStr41_1","2014-04-26");
		mapDateStr.put("dateStr42","2014-04-01 00:00:00 +0000");
		mapDateStr.put("dateStr42_1","2014-04");
		mapDateStr.put("dateStr43","2014-01-01 00:00:00 +0000");
		mapDateStr.put("dateStr43_1","2014");
		mapDateStr.put("dateStr44","2014-05-11 08:20:13.787 +0000");
		mapDateStr.put("dateStr44_1","2014-05-11 08:20:13,787");
		mapDateStr.put("dateStr45","2014-03-01 00:00:00 +0000");
		mapDateStr.put("dateStr45_1","2014.03");
		mapDateStr.put("dateStr46","2014-03-30 00:00:00 +0000");
		mapDateStr.put("dateStr48","2014-03-30 14:00:00 +0000");
		mapDateStr.put("dateStr50","2014-06-01 00:00:00 +0000");
		mapDateStr.put("dateStr50_1","20140601");
		mapDateStr.put("dateStr51","2014-07-22 10:52:03 +0000");
		mapDateStr.put("dateStr51_1","20140722105203");
		mapDateStr.put("dateStr52","2013-11-12 00:32:47.189 +0000");
		mapDateStr.put("dateStr52_1","1384216367189");
		mapDateStr.put("dateStr53","2013-11-12 00:32:47.111222 +0000");
		mapDateStr.put("dateStr53_1","1384216367111222");
		mapDateStr.put("dateStr54","2013-11-12 00:32:47.111222333 +0000");
		mapDateStr.put("dateStr54_1","1384216367111222333");
//		mapDateStr.put("dateStr46_1","2014.03.30 13:00:00 +1300");
//		mapDateStr.put("dateStr47","2014-03-30 00:00:00 -1400");
//		mapDateStr.put("dateStr47_1","2014.03.30 00:00:00 -1400");
//		mapDateStr.put("dateStr48_1","2014.03.30 00:00:00 -1400");
//		mapDateStr.put("dateStr49","2014-03-30 00:00:00 +1300");
//		mapDateStr.put("dateStr49_1","2014.03.30 00:00:00 +1300");
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
// 	@Test
	public void test() {
		for (Entry<String, String> entry : mapDateStr.entrySet()) {
			for (Class<?> dateType : dateTypes) {
				String dateStr = entry.getValue();
				System.out.println(entry.getKey() + " : " + dateStr);
				if (Instant.class == dateType) {
					int debugLine = 0;
				}
 				Object converted = DateConverterFacade.convert(dateStr, dateType);
 				System.out.println("\t" + "from String to " + dateType.getName() + " = " + converted);
				for (Class<?> dateTypeTo : dateTypes) {
					if (Instant.class == dateTypeTo) {
						int debugLine = 0;
					}
					Object convertedTo = DateConverterFacade.convert(converted, dateTypeTo);
	 				System.out.println("\t" + "from " + dateType.getName() + " to " + dateTypeTo.getName() 
	 						+ ". converted-value = " + convertedTo);
				}
 			}
 		}
	}
}
