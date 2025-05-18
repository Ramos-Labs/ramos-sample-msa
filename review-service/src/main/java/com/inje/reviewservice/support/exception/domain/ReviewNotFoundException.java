package com.inje.reviewservice.support.exception.domain;

import com.inje.reviewservice.support.enumeration.ResponseTypeCodeEnum;
import com.inje.reviewservice.support.exception.BaseRuntimeException;

/**
 * 리뷰 조회 시 해당 리뷰가 존재하지 않는 경우 발생하는 Exception.
 *
 * @author HakHyeon Song
 */
public class ReviewNotFoundException extends BaseRuntimeException {

    public ReviewNotFoundException(String loggingMessage) {
        super(ResponseTypeCodeEnum.NOT_FOUND, loggingMessage);
    }

    public ReviewNotFoundException(Throwable e) {
        super(ResponseTypeCodeEnum.NOT_FOUND, e);
    }
}
