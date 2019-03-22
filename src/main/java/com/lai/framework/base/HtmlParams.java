package com.lai.framework.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)  
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HtmlParams {
	
	public String lable();
	
	public String tips() default "";

	public boolean list() default false;
	
	public boolean search() default false;
	
	public boolean input() default false;
	
	public boolean must() default false;
	
	public boolean detail() default false;
}
