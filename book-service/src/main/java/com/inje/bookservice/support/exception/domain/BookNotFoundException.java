package com.inje.bookservice.support.exception.domain;

import com.inje.bookservice.support.enumeration.ResponseTypeCodeEnum;
import com.inje.bookservice.support.exception.BaseRuntimeException;

/**
 * 도서 조회 시 해당 도서가 존재하지 않는 경우 발생하는 Exception.
 *
 * @author HakHyeon Song
 */
public class BookNotFoundException extends BaseRuntimeException {

    public BookNotFoundException(String loggingMessage) {
        super(ResponseTypeCodeEnum.NOT_FOUND, loggingMessage);
    }

    public BookNotFoundException(Throwable e) {
        super(ResponseTypeCodeEnum.NOT_FOUND, e);
    }
}
