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

	// 作者属性
	String author();
	// 定义日期
	String date();
	// 定义版本号
	double currentVersion() default 1.0;
	// 定义修改
	String lastModified() default "N/A";
	//
	String lastModifiedBy() default "N/A";
	//
	String[] reviews();
	//
	String value();
}
