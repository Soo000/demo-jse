package wava.annotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Soosky
 * SuppressWarnings ѹ�ơ���ѹ��ѹ�ƣ���ѹһЩ���治��ʾ�����������ࡢ���ԡ���������������֮��
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
