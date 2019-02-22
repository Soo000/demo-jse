package wava.annotation.repeat;

public class Schedualer {

	@Schedual(dayOfMonth="last") // 每月最后一天
	@Schedual(dayOfWeek="friday", hour=23) // 每周五 23点
	public void prodicaCleanup() {
		
	}
	
}
