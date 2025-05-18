package com.inje.authorservice.domain.repository;

import com.inje.authorservice.domain.entity.AuthorEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author Entity에 대한 JPA Repository
 *
 * @author HakHyeon Song
 */
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    List<AuthorEntity> findAllByIdIn(Collection<Long> authorIds);
}
