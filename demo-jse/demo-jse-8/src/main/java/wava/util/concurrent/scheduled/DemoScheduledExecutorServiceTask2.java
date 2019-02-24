package wava.util.concurrent.scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DemoScheduledExecutorServiceTask2 extends TimerTask {

	private String jobName;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public DemoScheduledExecutorServiceTask2(String jobName) {
		this.jobName = jobName;
	}
	
	@Override
	public void run() {
		System.out.println(jobName + " is run at " + sdf.format(Calendar.getInstance().getTime()));
	}
	
	/**
	 * 计算从当前时间 currentDate开始，满足添加 dayOfWeek, hourOfDay, minuteOfHour, secondOfMiute 的最近时间
	 * @param currentDate
	 * @param dayOfWeek
	 * @param hourOfDay
	 * @param minuteOfHour
	 * @param secondOfMinute
	 * @return
	 */
	public Calendar getEarliestDate(Calendar currentDate, int dayOfWeek, int hourOfDay, 
			int minuteOfHour, int secondOfMinite) {
		
		// 计算当前时间 weekOfYear, dayOfWeek, hourOfDay, minuteOfHour, secondOfMinute 的值
		
		int currentWeekOfYear = currentDate.get(Calendar.WEEK_OF_YEAR); //
		System.out.println("currentWeekOfYear=" + currentWeekOfYear);
		int currentDayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK); // sunday, monday, ...
		System.out.println("currentDayOfWeek=" + currentDayOfWeek);
		int currentHourOfDay = currentDate.get(Calendar.HOUR_OF_DAY); // 24 小时制，如果时 16:55, 则显示 16
		System.out.println("currentHourOfDay=" + currentHourOfDay);
		int currentMinute = currentDate.get(Calendar.MINUTE); // eg: 17:02:23 结果为 1
		System.out.println("currentMinute=" + currentMinute);
		int currentSecond = currentDate.get(Calendar.SECOND); // eg: 17:02:23 结果为 23
		System.out.println("currentSecond=" + currentSecond);
		
		 // 如果输入条件中的dayOfWeek小于当前日期的dayOfWeek,则WEEK_OF_YEAR需要推迟一周  
        boolean weekLater = false;  
        if (dayOfWeek < currentDayOfWeek) {  
            weekLater = true;  
        } else if (dayOfWeek == currentDayOfWeek) {  
            // 当输入条件与当前日期的dayOfWeek相等时，如果输入条件中的  
            // hourOfDay小于当前日期的  
            // currentHour，则WEEK_OF_YEAR需要推迟一周      
            if (hourOfDay < currentHourOfDay) {  
                weekLater = true;  
            } else if (hourOfDay == currentHourOfDay) {  
                 // 当输入条件与当前日期的dayOfWeek, hourOfDay相等时，  
                 // 如果输入条件中的minuteOfHour小于当前日期的  
                // currentMinute，则WEEK_OF_YEAR需要推迟一周  
                if (minuteOfHour < currentMinute) {  
                    weekLater = true;  
                } else if (minuteOfHour == currentSecond) {  
                	// 当输入条件与当前日期的dayOfWeek, hourOfDay，   
                	// minuteOfHour相等时，如果输入条件中的  
                    // secondOfMinite小于当前日期的currentSecond，  
                	// 则WEEK_OF_YEAR需要推迟一周  
                    if (secondOfMinite < currentSecond) {  
                        weekLater = true;  
                    }  
                }  
            }  
        }
        
        if (weekLater) {  
            //设置当前日期中的WEEK_OF_YEAR为当前周推迟一周  
            currentDate.set(Calendar.WEEK_OF_YEAR, currentWeekOfYear + 1);  
        }
        
        // 设置当前日期中的DAY_OF_WEEK,HOUR_OF_DAY,MINUTE,SECOND为输入条件中的值。  
        currentDate.set(Calendar.DAY_OF_WEEK, dayOfWeek);  
        currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay);  
        currentDate.set(Calendar.MINUTE, minuteOfHour);  
        currentDate.set(Calendar.SECOND, secondOfMinite);  
        return currentDate; 
	}

	/**
	 * 实现了每星期二 16:38:10 调度任务的功能。
	 * 其核心在于根据当前时间推算出最近一个星期二 16:38:10 的绝对时间，然后计算与当前时间的时间差，作为调用 ScheduledExceutor 函数的参数。
	 * 计算最近时间要用到 java.util.calendar 的功能
	 * @param args
	 */
	public static void main(String[] args) {
		DemoScheduledExecutorServiceTask2 test = new DemoScheduledExecutorServiceTask2("job1");  
        // 获取当前时间  
        Calendar currentDate = Calendar.getInstance();  
        long currentDateLong = currentDate.getTime().getTime();  
        System.out.println("Current Date = " + currentDate.getTime().toString());  
        // 计算满足条件的最近一次执行时间  
        Calendar earliestDate = test.getEarliestDate(currentDate, 3, 16, 38, 10);  // 每星期二 16:38:10
        long earliestDateLong = earliestDate.getTime().getTime();
        System.out.println("Earliest Date = " + earliestDate.getTime().toString());  
        // 计算从当前时间到最近一次执行时间的时间间隔  
        long delay = earliestDateLong - currentDateLong;  
        // 计算执行周期为一星期  
        long period = 7 * 24 * 60 * 60 * 1000;  
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);  
        // 从现在开始delay毫秒之后，每隔一星期执行一次job1  
        service.scheduleAtFixedRate(test, delay, period, TimeUnit.MILLISECONDS); 
	}
}
