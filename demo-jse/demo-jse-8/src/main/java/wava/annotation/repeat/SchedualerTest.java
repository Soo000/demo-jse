package wava.annotation.repeat;

import java.lang.reflect.Method;

public class SchedualerTest {

	public static void main(String[] args) throws Exception {
	
		Class schedualerClass = Schedualer.class;
		Method method = schedualerClass.getDeclaredMethod("prodicaCleanup", null);
		Scheduals scheduals = (Scheduals) method.getAnnotation(Scheduals.class);
		for (Schedual schedual : scheduals.value()) {
			String dayOfMonth = schedual.dayOfMonth();
			String dayOfWeek = schedual.dayOfWeek();
			int hour = schedual.hour();
			
			System.out.println("dayOfMonth = " + dayOfMonth);
			System.out.println("dayOfWeek = " + dayOfWeek);
			System.out.println("hour = " + hour);
			
			System.out.println("--------");
		}
	}
	
}
