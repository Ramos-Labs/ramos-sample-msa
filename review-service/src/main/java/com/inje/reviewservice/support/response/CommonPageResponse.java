package com.inje.reviewservice.support.response;

import com.inje.reviewservice.support.enumeration.ResponseTypeCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * Pagination API 응답 format에 사용되는 Wrapper DTO
 *
 * @author HakHyeon Song
 */
@Schema(description = "공통 페이지네이션 응답 객체")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonPageResponse<T> {

    @Schema(description = "응답 Header")
    private Header header;
    @Schema(description = "응답 데이터")
    private List<T> data;
    @Schema(description = "Pagination Info")
    private PageInfo pageInfo;

    public static <U> CommonPageResponse<U> ok(Page<U> page) {
        Header header = Header.builder()
                .isSuccessful(true)
                .resultCode(ResponseTypeCodeEnum.SUCCESS.getResultCode())
                .build();

        PageInfo pageInfo = PageInfo.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .totalPage(page.getTotalPages())
                .totalSize(page.getTotalElements())
                .build();
        return new CommonPageResponse<>(header, page.getContent(), pageInfo);
    }

    public static <U> CommonPageResponse<U> error(ResponseTypeCodeEnum responseTypeCodeEnum) {
        Header header = Header.builder()
                .isSuccessful(false)
                .resultCode(responseTypeCodeEnum.getResultCode())
                .build();

        PageInfo pageInfo = new PageInfo();
        return new CommonPageResponse<>(header, Collections.emptyList(), pageInfo);
    }
}
