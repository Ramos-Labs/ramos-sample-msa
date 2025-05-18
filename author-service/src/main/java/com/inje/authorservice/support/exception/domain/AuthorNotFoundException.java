package com.inje.authorservice.support.exception.domain;

import com.inje.authorservice.support.enumeration.ResponseTypeCodeEnum;
import com.inje.authorservice.support.exception.BaseRuntimeException;

/**
 * 저자 조회 시 해당 저자가 존재하지 않는 경우 발생하는 Exception.
 *
 * @author HakHyeon Song
 */
public class AuthorNotFoundException extends BaseRuntimeException {

    public AuthorNotFoundException(String loggingMessage) {
        super(ResponseTypeCodeEnum.NOT_FOUND, loggingMessage);
    }

    public AuthorNotFoundException(Throwable e) {
        super(ResponseTypeCodeEnum.NOT_FOUND, e);
    }
}
