package com.inje.bookservice.adapter;

import com.inje.bookservice.adapter.dto.response.AuthorResponse;
import com.inje.bookservice.adapter.fallback.AuthorQueryClientFallbackFactory;
import com.inje.bookservice.config.openfeign.FeignClientConfig;
import com.inje.bookservice.support.response.CommonResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 저자 서비스 API 중 Query 호출을 위한 FeignClient
 *
 * @author HakHyeon Song
 */
@FeignClient(name = "author-service-client", url = "${internal.author.url}", path = "/authors", configuration = FeignClientConfig.class, fallbackFactory = AuthorQueryClientFallbackFactory.class)
public interface AuthorQueryClient {

    @GetMapping("/{authorId}")
    ResponseEntity<CommonResponse<AuthorResponse>> getAuthor(@PathVariable("authorId") Long authorId);

    @GetMapping("/info")
    ResponseEntity<CommonResponse<List<AuthorResponse>>> getAuthorInfos(@RequestParam("authorIds") List<Long> authorIds);
}
