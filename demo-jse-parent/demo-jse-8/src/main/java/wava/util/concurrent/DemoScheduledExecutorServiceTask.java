package wava.util.concurrent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DemoScheduledExecutorServiceTask implements Runnable {

	private String jobName;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public DemoScheduledExecutorServiceTask(String jobName) {
		this.jobName = jobName;
	}
	
	@Override
	public void run() {
		if (jobName.equalsIgnoreCase("job1")) {
			System.out.println(jobName + " is run at " + sdf.format(Calendar.getInstance().getTime()));
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(jobName + " is run at " + sdf.format(Calendar.getInstance().getTime()));
		}
	}

}
