package com.inje.reviewservice.domain.service;

import com.inje.reviewservice.domain.api.dto.response.ReviewResponse;
import java.util.List;

/**
 * 리뷰 조회 서비스 interface.
 *
 * @author HakHyeon Song
 */
public interface ReviewQueryService {


    /**
     * 도서 ID에 해당하는 리뷰 목록 조회
     *
     * @param bookId 리뷰 조회 대상 도서 ID
     * @return 리뷰 조회 결과 목록
     */
    List<ReviewResponse> retrieveReviewsByBookId(Long bookId);
}
