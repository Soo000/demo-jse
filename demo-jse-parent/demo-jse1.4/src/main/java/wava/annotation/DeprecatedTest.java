package wava.annotation;

/**
 * @author Soosky
 * Deprecated ע���ʾ���ã����������޳ɣ����������ࡢ���ԡ�����֮��
 */
public class DeprecatedTest {
	Circle circle = new Circle();
	double pi = Circle.PI;	
	double area = circle.caculateArea(2.0);
}

@Deprecated
class Circle {
	
	@Deprecated
	public static final double PI = 3.1415926;
	
	@Deprecated
	public double caculateArea(double raduis) {
		return Math.PI * (raduis * raduis);
	}
}
