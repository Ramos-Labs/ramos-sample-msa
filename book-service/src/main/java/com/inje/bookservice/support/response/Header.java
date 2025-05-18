package com.inje.bookservice.support.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * API 응답 형식으로 사용할 공통 Header
 *
 * @author HakHyeon Song
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Header {

    /**
     * 성공/실패 여부
     */
    private Boolean isSuccessful;

    /**
     * 결과 코드
     */
    private int resultCode;
}
