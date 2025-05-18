package com.inje.bookservice.domain.service;

import com.inje.bookservice.adapter.AuthorQueryClient;
import com.inje.bookservice.adapter.ReviewQueryClient;
import com.inje.bookservice.adapter.dto.response.AuthorResponse;
import com.inje.bookservice.adapter.dto.response.ReviewResponse;
import com.inje.bookservice.adapter.utils.FeignResponseUtil;
import com.inje.bookservice.domain.api.dto.response.BookDetailResponse;
import com.inje.bookservice.domain.api.dto.response.BookSummaryResponse;
import com.inje.bookservice.domain.entity.BookEntity;
import com.inje.bookservice.domain.repository.BookRepository;
import com.inje.bookservice.support.exception.domain.BookNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 도서 조회 서비스 implementation class.
 *
 * @author HakHyeon Song
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookQueryServiceImpl implements BookQueryService {

    private final BookRepository bookRepository;
    private final AuthorQueryClient authorQueryClient;
    private final ReviewQueryClient reviewQueryClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public BookDetailResponse retrieveBook(Long bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with ID %d not found", bookId)));

        AuthorResponse authorResponse = FeignResponseUtil.callAndUnwrap(
                () -> authorQueryClient.getAuthor(bookEntity.getAuthorId()),
                "AuthorService"
        );

        List<ReviewResponse> reviewResponses = FeignResponseUtil.callAndUnwrap(
                () -> reviewQueryClient.getReviewsByBookId(bookId),
                "ReviewService"
        );

        return BookDetailResponse.of(bookEntity, authorResponse, reviewResponses);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<BookSummaryResponse> retrieveBookSummaries(Pageable pageable) {
        Page<BookEntity> bookEntities = bookRepository.findAll(pageable);

        List<Long> authorIds = bookEntities.stream()
                .map(BookEntity::getAuthorId)
                .distinct()
                .toList();

        List<AuthorResponse> authors = FeignResponseUtil.callAndUnwrap(
                () -> authorQueryClient.getAuthorInfos(authorIds),
                "AuthorService"
        );

        Map<Long, AuthorResponse> authorMap = authors.stream()
                .collect(Collectors.toMap(AuthorResponse::getId, Function.identity()));

        return bookEntities.map(book -> BookSummaryResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .publishedDate(book.getPublishedDate())
                .authorName(
                        Optional.ofNullable(authorMap.get(book.getAuthorId()))
                                .map(AuthorResponse::getName)
                                .orElse("정보 없음")
                )
                .build()
        );
    }

}
