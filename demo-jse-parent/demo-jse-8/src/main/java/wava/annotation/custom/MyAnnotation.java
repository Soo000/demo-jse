package wava.annotation.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.TYPE, ElementType.CONSTRUCTOR})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

	// ��������
	String author();
	// ��������
	String date();
	// ����汾��
	double currentVersion() default 1.0;
	// �����޸�
	String lastModified() default "N/A";
	//
	String lastModifiedBy() default "N/A";
	//
	String[] reviews();
	//
	String value();
}
