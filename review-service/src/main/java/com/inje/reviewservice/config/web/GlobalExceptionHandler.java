package com.inje.reviewservice.config.web;

import com.inje.reviewservice.support.enumeration.ResponseTypeCodeEnum;
import com.inje.reviewservice.support.enumeration.ResponseTypeCodeEnumInterface;
import com.inje.reviewservice.support.exception.BaseRuntimeException;
import com.inje.reviewservice.support.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 공통 Exception Handler
 *
 * @author HakHyeon Song
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<CommonResponse<Void>> handleBaseRuntimeException(BaseRuntimeException e) {
        ResponseTypeCodeEnumInterface responseType = e.getExceptionCode();
        logExceptionMessage(responseType, e);

        return new ResponseEntity<>(CommonResponse.error(responseType), responseType.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleException(Exception e) {
        ResponseTypeCodeEnum responseType = ResponseTypeCodeEnum.INTERNAL_SERVER_ERROR;
        logExceptionMessage(responseType, e);

        return new ResponseEntity<>(CommonResponse.error(responseType), responseType.getHttpStatus());
    }

    private void logExceptionMessage(ResponseTypeCodeEnumInterface responseTypeInterface, Throwable throwable) {
        log.error("StatusCode: {}, ExceptionCode: {}, ExceptionMessage: {}",
                responseTypeInterface.getHttpStatus(), responseTypeInterface.getResultCode().toString(), throwable.getMessage(), throwable);
    }
}
