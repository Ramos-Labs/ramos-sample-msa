package com.inje.bookservice.domain.api;

import com.inje.bookservice.domain.api.dto.response.BookDetailResponse;
import com.inje.bookservice.domain.api.dto.response.BookSummaryResponse;
import com.inje.bookservice.domain.service.BookQueryService;
import com.inje.bookservice.support.response.CommonPageResponse;
import com.inje.bookservice.support.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book Query API", description = "도서 조회 API")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookQueryController {

    private final BookQueryService bookQueryService;

    @GetMapping
    @Operation(summary = "도서 목록 조회 API", description = "도서 목록을 조회한다. 요약정보에 대한 페이지네이션을 리턴한다.")
    public ResponseEntity<CommonPageResponse<BookSummaryResponse>> getBooks(Pageable pageable) {
        Page<BookSummaryResponse> bookSummaryResponses = bookQueryService.retrieveBookSummaries(pageable);
        return ResponseEntity.ok(CommonPageResponse.ok(bookSummaryResponses));
    }

    @GetMapping("/{bookId}")
    @Operation(summary = "도서 상세 조회 API", description = "도서에 대한 상세 정보를 조회한다.")
    public ResponseEntity<CommonResponse<BookDetailResponse>> getBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(CommonResponse.ok(bookQueryService.retrieveBook(bookId)));
    }
}
