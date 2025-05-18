package com.inje.bookservice.domain.api.dto.response;

import com.inje.bookservice.adapter.dto.response.AuthorResponse;
import com.inje.bookservice.adapter.dto.response.ReviewResponse;
import com.inje.bookservice.domain.entity.BookEntity;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 도서 상세 정보 응답 dto.
 *
 * @author HakHyeon Song
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailResponse {

    private Long id;
    private String title;
    private LocalDateTime publishedDate;
    private AuthorResponse author;
    private List<ReviewResponse> reviews;

    public static BookDetailResponse of(BookEntity bookEntity, AuthorResponse author, List<ReviewResponse> reviews) {
        return BookDetailResponse.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .publishedDate(bookEntity.getPublishedDate())
                .author(author)
                .reviews(reviews)
                .build();
    }
}
