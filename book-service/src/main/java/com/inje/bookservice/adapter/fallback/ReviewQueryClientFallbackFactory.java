package com.inje.bookservice.adapter.fallback;

import com.inje.bookservice.adapter.ReviewQueryClient;
import com.inje.bookservice.adapter.dto.response.ReviewResponse;
import com.inje.bookservice.support.constants.MessageConstants;
import com.inje.bookservice.support.response.CommonResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 장애 상황을 위한 Review Query Client Fallback Factory.
 *
 * @author HakHyeon Song
 */
@Slf4j
@Component
public class ReviewQueryClientFallbackFactory implements FallbackFactory<ReviewQueryClient> {

    @Override
    public ReviewQueryClient create(Throwable cause) {
        log.error("Fallback 응답 반환 - Review Service 호출 실패: {}", cause.getMessage(), cause);

        return bookId -> {
            CommonResponse<List<ReviewResponse>> body = CommonResponse.ok(List.of(dummyReview(bookId)));
            return ResponseEntity.ok(body);
        };
    }

    private ReviewResponse dummyReview(Long bookId) {
        return ReviewResponse.builder()
                .id(-1L)
                .bookId(bookId)
                .reviewer(MessageConstants.UNKNOWN_FALLBACK_STRING)
                .rating(-1)
                .comment(MessageConstants.UNKNOWN_FALLBACK_STRING)
                .createdAt(null)
                .build();
    }
}
