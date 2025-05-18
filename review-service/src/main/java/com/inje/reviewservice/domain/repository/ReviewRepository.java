package com.inje.reviewservice.domain.repository;

import com.inje.reviewservice.domain.entity.ReviewEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Review Entity에 대한 JPA Repository
 *
 * @author HakHyeon Song
 */
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findAllByBookId(Long bookId);
}
