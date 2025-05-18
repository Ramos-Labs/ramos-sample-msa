package com.inje.authorservice.support.response;

import com.inje.authorservice.support.enumeration.ResponseTypeCodeEnum;
import com.inje.authorservice.support.enumeration.ResponseTypeCodeEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 일반 API 응답 format에 사용되는 Wrapper DTO
 *
 * @author HakHyeon Song
 */
@Schema(description = "공통 응답 객체")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    @Schema(description = "응답 Header")
    private Header header;

    @Schema(description = "응답 데이터")
    private T data;

    public static CommonResponse<Void> ok() {
        return ok(null);
    }

    public static <U> CommonResponse<U> ok(U data) {
        Header header = Header.builder()
                .isSuccessful(true)
                .resultCode(ResponseTypeCodeEnum.SUCCESS.getResultCode())
                .build();
        return new CommonResponse<>(header, data);
    }

    public static <U> CommonResponse<U> error(int code) {
        return error(code, null);
    }

    public static <U> CommonResponse<U> error(int code, U data) {
        Header header = Header.builder()
                .isSuccessful(false)
                .resultCode(code)
                .build();
        return new CommonResponse<>(header, data);
    }

    public static <U> CommonResponse<U> error(ResponseTypeCodeEnumInterface code) {
        return error(code, null);
    }

    public static <U> CommonResponse<U> error(ResponseTypeCodeEnumInterface code, U data) {
        return error(code.getResultCode(), data);
    }
}
