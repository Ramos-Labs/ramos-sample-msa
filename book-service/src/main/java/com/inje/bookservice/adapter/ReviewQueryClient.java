package com.inje.bookservice.adapter;

import com.inje.bookservice.adapter.dto.response.ReviewResponse;
import com.inje.bookservice.adapter.fallback.ReviewQueryClientFallbackFactory;
import com.inje.bookservice.config.openfeign.FeignClientConfig;
import com.inje.bookservice.support.response.CommonResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 리뷰 서비스 API 중 Query 호출을 위한 FeignClient
 *
 * @author HakHyeon Song
 */
@FeignClient(name = "review-service-client", url = "${internal.review.url}", path = "/reviews", configuration = FeignClientConfig.class, fallbackFactory = ReviewQueryClientFallbackFactory.class)
public interface ReviewQueryClient {

    @GetMapping("/{bookId}")
    ResponseEntity<CommonResponse<List<ReviewResponse>>> getReviewsByBookId(@PathVariable("bookId") Long bookId);
}
