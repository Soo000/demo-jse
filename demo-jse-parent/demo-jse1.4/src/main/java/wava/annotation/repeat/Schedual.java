package wava.annotation.repeat;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Scheduals.class)
public @interface Schedual {

	String dayOfMonth() default "";
	String dayOfWeek() default "";
	int hour() default 0;
	
}
