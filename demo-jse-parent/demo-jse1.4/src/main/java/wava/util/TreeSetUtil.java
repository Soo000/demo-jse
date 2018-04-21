package wava.util;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class TreeSetUtil {
	
	@Test
	public void testTreeSet0() {
		Set set = new TreeSet();
		set.add(3);
		set.add(5);
		set.add(1);
		set.add(2);
		set.add(8);
		
		for (Object o: set) {
			System.out.println(o);
		}
	}

	@Test
	public void testTreeSet() {
		Set set = new TreeSet();
		set.add(new String("AA"));
		set.add(new String("AA"));
		set.add("JJ");
		set.add("GG");
		set.add("MM");
		
		for (Object o: set) {
			System.out.println(o);
		}
		
		String a;
	}
	
}
