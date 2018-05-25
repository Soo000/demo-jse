package wava.annotation.reflect;

import wava.annotation.custom.MyAnnotation;
import wava.annotation.custom.MyAnnotationTest;

public class ReadAnnotation {

	public static void main(String[] args) {
		
		Class myAnnotationTestClass = MyAnnotationTest.class;
		MyAnnotation myAnnotation = (MyAnnotation) myAnnotationTestClass.getAnnotation(MyAnnotation.class);
		
		String author = myAnnotation.author();
		String date = myAnnotation.date();
		String lastModified = myAnnotation.lastModified();
		String lastModifiedBy = myAnnotation.lastModifiedBy();
		
		System.out.println("author = " + author);
		System.out.println("date = " + date);
		System.out.println("lastModified = " + lastModified);
		System.out.println("lastModifiedBy = " + lastModifiedBy);
	}
	
}
