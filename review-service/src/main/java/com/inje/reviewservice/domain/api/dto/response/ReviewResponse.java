package com.inje.reviewservice.domain.api.dto.response;

import com.inje.reviewservice.domain.entity.ReviewEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 리뷰 조회 API 응답 data.
 *
 * @author HakHyeon Song
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    @Schema(description = "리뷰 ID")
    private Long id;
    @Schema(description = "도서 ID")
    private Long bookId;
    @Schema(description = "리뷰어")
    private String reviewer;
    @Schema(description = "평점")
    private Integer rating;
    @Schema(description = "리뷰 내용")
    private String comment;
    @Schema(description = "작성 일시")
    private LocalDateTime createdAt;

    public static ReviewResponse of(ReviewEntity reviewEntity) {
        return ReviewResponse.builder()
                .id(reviewEntity.getId())
                .bookId(reviewEntity.getBookId())
                .reviewer(reviewEntity.getReviewer())
                .rating(reviewEntity.getRating())
                .comment(reviewEntity.getComment())
                .createdAt(reviewEntity.getCreatedAt())
                .build();
    }
}
