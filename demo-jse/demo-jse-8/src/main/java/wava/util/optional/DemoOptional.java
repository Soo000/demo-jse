package wava.util.optional;

import java.util.List;
import java.util.Optional;

public class DemoOptional {

	public static void main(String[] args) {
		testIsPresent3();
	}

	public static void testIsPresent() {
		Optional<String> fullName = Optional.ofNullable(null);
		System.out.println("Full Name is " + fullName.isPresent());
		System.out.println("Full Name is " + fullName.orElseGet(() -> "[none]"));
	}

	public static void testIsPresent2() {
		List<String> myList = null;
		Optional.ofNullable(myList).ifPresent(item -> {
			System.out.print(item);
		});
	}

	public static void testIsPresent3() {
		UserDO userDO = null;
		
		//Optional<UserDO> uOptional = Optional.ofNullable(userDO);
		
		UserDTO userDTO = Optional.ofNullable(userDO).map(uDO -> {
			UserDTO uDTO = new UserDTO();
			uDTO.setId(uDO.getId());
			uDTO.setName(uDO.getName());
			return uDTO;
		}).orElseThrow(() -> new RuntimeException("uDTO is null."));

		System.out.println("userDTO = " + userDTO);
	}

}
