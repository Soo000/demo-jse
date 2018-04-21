package wava.annotation;

/**
 * @author Soosky
 * Override注解对父类方法重写进行校验
 */
public class OverrideTest extends Object {

	@Override
	public String toString() {
		return super.toString();
	}
	
}
