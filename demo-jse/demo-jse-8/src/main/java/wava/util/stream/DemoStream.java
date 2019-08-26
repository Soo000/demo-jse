package wava.util.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DemoStream {
	
	private void createByArray() {
		IntStream intStream = Arrays.stream(new int[] {1, 2, 3});
		System.out.println(intStream);
	}
	
	private void createByCollection() {
		List<String> list = new ArrayList<>();
		// 串行 Stream
		Stream<String> stream = list.stream();
		// 并行 Stream
		Stream<String> parallelSteam = list.parallelStream();
		
		System.out.println(stream);
		System.out.println(parallelSteam);
	}
	
	private void createByMethod() {
		Stream<Object> stream = Stream.of(new Object());
		System.out.println(stream);
	}
	
}
