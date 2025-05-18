package com.inje.bookservice.adapter.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 리뷰 조회 응답 data.
 *
 * @author HakHyeon Song
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Long id;
    private Long bookId;
    private String reviewer;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}
