package com.inje.bookservice.adapter.dto.response;

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
    private Long id;
    private String name;
    private String bio;
}
