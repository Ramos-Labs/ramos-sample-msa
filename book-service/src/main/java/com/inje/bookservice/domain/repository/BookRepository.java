package com.inje.bookservice.domain.repository;

import com.inje.bookservice.domain.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Book Entity에 대한 JPA Repository
 *
 * @author HakHyeon Song
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
