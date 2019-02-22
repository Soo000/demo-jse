package wava.annotation.repeat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Scheduals {

	Schedual[] value();
}
