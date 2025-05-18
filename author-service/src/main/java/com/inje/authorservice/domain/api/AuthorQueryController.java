package com.inje.authorservice.domain.api;

import com.inje.authorservice.domain.api.dto.response.AuthorResponse;
import com.inje.authorservice.domain.service.AuthorQueryService;
import com.inje.authorservice.support.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Author Query API", description = "저자 조회 API")
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorQueryController {

    private final AuthorQueryService authorQueryService;

    @GetMapping("/{authorId}")
    @Operation(summary = "저자 조회 API", description = "저자 정보를 조회한다.")
    public ResponseEntity<CommonResponse<AuthorResponse>> getAuthor(@PathVariable("authorId") Long authorId) {
        return ResponseEntity.ok(CommonResponse.ok(authorQueryService.retrieveAuthor(authorId)));
    }

    @GetMapping("/info")
    @Operation(summary = "저자 목록 조회 API", description = "저자 목록을 조회한다. 저자 ID를 bulk성으로 입력 후 매칭되는 저자 목록을 리턴한다.")
    public ResponseEntity<CommonResponse<List<AuthorResponse>>> getAuthorInfos(@RequestParam("authorIds") List<Long> authorIds) {
        List<AuthorResponse> authorResponses = authorQueryService.retrieveAuthorInfos(authorIds);
        return ResponseEntity.ok(CommonResponse.ok(authorResponses));
    }
}
