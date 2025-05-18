package com.inje.authorservice.domain.service;

import com.inje.authorservice.domain.api.dto.response.AuthorResponse;
import java.util.List;

/**
 * 저자 조회 서비스 interface.
 *
 * @author HakHyeon Song
 */
public interface AuthorQueryService {

    /**
     * 저자 조회
     *
     * @param authorId 조회 대상 저자 ID
     * @return 저자 조회 결과
     */
    AuthorResponse retrieveAuthor(Long authorId);

    /**
     * 저자 목록 조회
     *
     * @param authorIds 조회 대상 저자 ID 리스트
     * @return 저자 목록 조회 결과
     */
    List<AuthorResponse> retrieveAuthorInfos(List<Long> authorIds);
}
