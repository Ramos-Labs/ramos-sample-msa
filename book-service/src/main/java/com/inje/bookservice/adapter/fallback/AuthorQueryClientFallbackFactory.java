package com.inje.bookservice.adapter.fallback;

import com.inje.bookservice.adapter.AuthorQueryClient;
import com.inje.bookservice.adapter.dto.response.AuthorResponse;
import com.inje.bookservice.support.constants.MessageConstants;
import com.inje.bookservice.support.response.CommonResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 장애 상황을 위한 Author Query Client Fallback Factory.
 *
 * @author HakHyeon Song
 */
@Slf4j
@Component
public class AuthorQueryClientFallbackFactory implements FallbackFactory<AuthorQueryClient> {

    @Override
    public AuthorQueryClient create(Throwable cause) {
        log.error("Fallback 응답 반환 - Author Service 호출 실패: {}", cause.getMessage(), cause);

        return new AuthorQueryClient() {
            @Override
            public ResponseEntity<CommonResponse<AuthorResponse>> getAuthor(Long authorId) {
                CommonResponse<AuthorResponse> body = CommonResponse.ok(dummyAuthor(authorId));
                return ResponseEntity.ok(body);
            }

            @Override
            public ResponseEntity<CommonResponse<List<AuthorResponse>>> getAuthorInfos(List<Long> authorIds) {
                List<AuthorResponse> dummyList = authorIds.stream()
                        .map(id -> dummyAuthor(id))
                        .collect(Collectors.toList());
                CommonResponse<List<AuthorResponse>> body = CommonResponse.ok(dummyList);
                return ResponseEntity.ok(body);
            }
        };
    }

    private AuthorResponse dummyAuthor(Long authorId) {
        return AuthorResponse.builder()
                .id(authorId)
                .name(MessageConstants.UNKNOWN_FALLBACK_STRING)
                .bio(MessageConstants.UNKNOWN_FALLBACK_STRING)
                .build();
    }
}
