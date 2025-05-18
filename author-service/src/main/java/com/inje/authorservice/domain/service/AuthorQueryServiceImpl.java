package com.inje.authorservice.domain.service;

import com.inje.authorservice.domain.api.dto.response.AuthorResponse;
import com.inje.authorservice.domain.entity.AuthorEntity;
import com.inje.authorservice.domain.repository.AuthorRepository;
import com.inje.authorservice.support.exception.domain.AuthorNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 저자 조회 서비스 implementation class.
 *
 * @author HakHyeon Song
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorQueryServiceImpl implements AuthorQueryService {

    private final AuthorRepository authorRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorResponse retrieveAuthor(Long authorId) {
        AuthorEntity authorEntity = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Author with ID %d not found", authorId)));
        return AuthorResponse.of(authorEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuthorResponse> retrieveAuthorInfos(List<Long> authorIds) {
        return authorRepository.findAllByIdIn(authorIds).stream()
                .map(AuthorResponse::of)
                .collect(Collectors.toList());
    }
}
