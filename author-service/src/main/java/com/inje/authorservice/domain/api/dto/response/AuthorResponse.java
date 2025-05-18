package com.inje.authorservice.domain.api.dto.response;

import com.inje.authorservice.domain.entity.AuthorEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 저자 조회 API 응답 data.
 *
 * @author HakHyeon Song
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {

    @Schema(description = "저자 ID")
    private Long id;
    @Schema(description = "저자명")
    private String name;
    @Schema(description = "저자 정보")
    private String bio;

    public static AuthorResponse of(AuthorEntity authorEntity) {
        return AuthorResponse.builder()
                .id(authorEntity.getId())
                .name(authorEntity.getName())
                .bio(authorEntity.getBio())
                .build();
    }
}
