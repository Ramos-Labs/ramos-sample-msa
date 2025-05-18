package com.inje.reviewservice.domain.api;

import com.inje.reviewservice.domain.api.dto.response.ReviewResponse;
import com.inje.reviewservice.domain.service.ReviewQueryService;
import com.inje.reviewservice.support.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Review Query API", description = "리뷰 조회 API")
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/{bookId}")
    @Operation(summary = "리뷰 목록 조회 API", description = "조회 대상 도서의 리뷰 목록을 조회한다. 도서 ID에 매칭되는 리뷰 목록을 리턴한다.")
    public ResponseEntity<CommonResponse<List<ReviewResponse>>> getReviewsByBookId(@PathVariable("bookId") Long bookId) {
        return ResponseEntity.ok(CommonResponse.ok(reviewQueryService.retrieveReviewsByBookId(bookId)));
    }
}
