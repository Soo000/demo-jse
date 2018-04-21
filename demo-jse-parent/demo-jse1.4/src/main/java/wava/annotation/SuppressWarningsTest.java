package wava.annotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Soosky
 * SuppressWarnings 压制、镇压；压制，镇压一些警告不显示；可以用在类、属性、方法、方法变量之上
 */
public class SuppressWarningsTest {
	
	@SuppressWarnings("rawtypes")
	List list = new ArrayList();

	@SuppressWarnings("deprecation")
	public static void printNowDate() {
		Date now = new Date();
		int year = now.getYear() + 1900;
		int month = now.getMonth() + 1;
		int date = now.getDate();
		System.out.println(year + " " + month + " " + date);
		
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
	}
	
	public static void main(String[] args) {
		printNowDate();
	}
}
