package wava.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

public class DemoTimerTask extends TimerTask {

	private String taskName;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public DemoTimerTask(String taskName) {
		this.taskName = taskName;
	}
	
	@Override
	public void run() {
		if (taskName.equalsIgnoreCase("job1")) {
			System.out.println(taskName + " is run at " + sdf.format(Calendar.getInstance().getTime()));
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(taskName + " is run at " + sdf.format(Calendar.getInstance().getTime()));
		}
	}

}
