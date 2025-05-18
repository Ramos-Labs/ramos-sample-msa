package com.inje.bookservice.domain.api.dto.response;

import com.inje.bookservice.adapter.dto.response.AuthorResponse;
import com.inje.bookservice.domain.entity.BookEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 도서 요약 정보 응답 dto.
 *
 * @author HakHyeon Song
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookSummaryResponse {

    @Schema(description = "도서 ID")
    private Long id;
    @Schema(description = "도서명")
    private String title;
    @Schema(description = "출판일")
    private LocalDateTime publishedDate;
    @Schema(description = "저자")
    private String authorName;

    public static BookSummaryResponse of(BookEntity bookEntity, AuthorResponse authorResponse) {
        return BookSummaryResponse.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .publishedDate(bookEntity.getPublishedDate())
                .authorName(authorResponse.getName())
                .build();
    }
}
