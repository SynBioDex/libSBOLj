package org.oboparser.obo.annotations;

import java.lang.annotation.Retention;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Relates {
	public String value() default "";
}
