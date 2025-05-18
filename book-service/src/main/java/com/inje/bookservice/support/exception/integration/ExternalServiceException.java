package com.inje.bookservice.support.exception.integration;

import com.inje.bookservice.support.enumeration.ResponseTypeCodeEnum;
import com.inje.bookservice.support.exception.BaseRuntimeException;

/**
 * 외부 API 호출 시 응답 데이터가 존재하지 않는 경우 발생하는 Exception.
 *
 * @author HakHyeon Song
 */
public class ExternalServiceException extends BaseRuntimeException {

    public ExternalServiceException(String loggingMessage) {
        super(ResponseTypeCodeEnum.EXTERNAL_SERVER_ERROR, loggingMessage);
    }

    public ExternalServiceException(Throwable e) {
        super(ResponseTypeCodeEnum.EXTERNAL_SERVER_ERROR, e);
    }
}
