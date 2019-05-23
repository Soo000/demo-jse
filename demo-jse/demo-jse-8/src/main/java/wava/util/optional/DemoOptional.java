package wava.util.optional;

import java.util.Optional;

public class DemoOptional {

	public static void main(String[] args) {
		Optional<String> fullName = Optional.ofNullable(null);
		System.out.println("Full Name is " + fullName.isPresent());
		System.out.println("Full Name is " + fullName.orElseGet(() -> "[none]"));
	}
	
}
