package com.inje.authorservice.support.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 페이지네이션 API의 공통 응답 형식으로 사용할 Page Info
 *
 * @author HakHyeon Song
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PageInfo {

    /**
     * 현재 page 번호
     */
    private int page;

    /**
     * 현재 page에 포함된 요소의 개수
     */
    private int size;

    /**
     * 전체 page의 수
     */
    private int totalPage;

    /**
     * 전체 요소의 개수
     */
    private long totalSize;
}
