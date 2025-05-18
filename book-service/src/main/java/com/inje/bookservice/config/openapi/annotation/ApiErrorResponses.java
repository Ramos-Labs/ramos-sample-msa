package com.inje.bookservice.config.openapi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * API의 에러 응답 목록을 명시하기 위한 annotation.
 *
 * @author HakHyeon Song
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorResponses {
	ApiErrorResponse[] value();
}
