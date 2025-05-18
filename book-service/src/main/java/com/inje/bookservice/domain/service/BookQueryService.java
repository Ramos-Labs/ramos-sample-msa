package com.inje.bookservice.domain.service;

import com.inje.bookservice.domain.api.dto.response.BookDetailResponse;
import com.inje.bookservice.domain.api.dto.response.BookSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 도서 조회 서비스 interface
 *
 * @author HakHyeon Song
 */
public interface BookQueryService {

    /**
     * 도서 상세 조회.
     *
     * @param bookId 조회 대상 도서 ID
     * @return 도서 상세 조회 결과
     */
    BookDetailResponse retrieveBook(Long bookId);

    /**
     * 도서 페이지네이션 조회.
     *
     * @param pageable Pageable
     * @return 도서 페이지네이션 조회 결과
     */
    Page<BookSummaryResponse> retrieveBookSummaries(Pageable pageable);
}
