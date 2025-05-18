package com.inje.reviewservice.config.openapi.annotation;

import com.inje.reviewservice.support.enumeration.ResponseTypeCodeEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * API의 에러 응답을 명시하기 위한 annotation.
 *
 * @author HakHyeon Song
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorResponse {

	ResponseTypeCodeEnum responseType();

	String description() default "";
}
