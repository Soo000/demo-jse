package wava.annotation;

/**
 * 
 * @author Soosky
 * FunctionalInterface 注解表示一个接口是一个函数式接口
 */
public class FunctionalInterfaceTest {

	public Greeting greeting;
	
	public FunctionalInterfaceTest(Greeting greeting) {
		this.greeting = greeting;
	}
	
	public static void main(String[] args) {
		// 使用匿名内部实现接口
		/*FunctionalInterfaceTest functionalInterfaceTest = new FunctionalInterfaceTest(new Greeting() {

			@Override
			public void greet(String name) {
				System.out.println("Hello " + name + " !");
			}
			
		});
		
		*/
		
		// 使用 lamb表达式实现（函数式接口，只能有一个抽象方法）
		FunctionalInterfaceTest functionalInterfaceTest = new FunctionalInterfaceTest(name -> System.out.println("Hello " + name));
		
		functionalInterfaceTest.greeting.greet("Wangke");
	}
	
}

/**
 * 函数式接口，只能有一个抽象方法
 *
 */
@FunctionalInterface
interface Greeting {
	void greet(String name);
}
