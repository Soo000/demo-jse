package wava.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	
	public static void main(String[] args) throws Exception {
		calTimeLongBetweenTwoDate();
	}
	
	private static void calTimeLongBetweenTwoDate() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse("2017-12-16 11:37:10");
		
		Date endDate = sdf.parse("2017-12-16 11:37:15");
		
		long timeLong = (endDate.getTime() - startDate.getTime()) / 1000;
		
		System.out.println(timeLong);
	}
	
}
