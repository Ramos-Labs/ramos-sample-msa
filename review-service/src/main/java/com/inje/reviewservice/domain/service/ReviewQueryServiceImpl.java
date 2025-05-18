package com.inje.reviewservice.domain.service;

import com.inje.reviewservice.domain.api.dto.response.ReviewResponse;
import com.inje.reviewservice.domain.repository.ReviewRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 리뷰 조회 서비스 implementation class.
 *
 * @author HakHyeon Song
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReviewResponse> retrieveReviewsByBookId(Long bookId) {
        return reviewRepository.findAllByBookId(bookId).stream()
                .map(ReviewResponse::of)
                .collect(Collectors.toList());
    }
}
