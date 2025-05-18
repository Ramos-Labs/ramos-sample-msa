package com.inje.bookservice.adapter.utils;

import com.inje.bookservice.support.exception.integration.ExternalServiceException;
import com.inje.bookservice.support.response.CommonResponse;
import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

/**
 * FeignClient 호출 응답 관련 util class.
 *
 * @author HakHyeon Song
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeignResponseUtil {

    /**
     * FeignClient 호출 이후 공통 응답 포멧에 해당하는 CommonResponse<T>에서 data만 꺼내 리턴. <br>
     * 리턴 이후 body나 data가 null이면 ExternalServiceException을 발생시킨다.
     *
     * @param feignCall FeignClient 호출 Supplier
     * @param serviceName 외부 마이크로서비스 이름
     * @param <T> CommonResponse로 감싸진 실제 data
     * @return 최종 응답 data
     */
    public static <T> T callAndUnwrap(Supplier<ResponseEntity<CommonResponse<T>>> feignCall, String serviceName) {
        ResponseEntity<CommonResponse<T>> responseEntity = feignCall.get();
        CommonResponse<T> commonResponse = responseEntity.getBody();
        if (commonResponse == null || commonResponse.getData() == null) {
            throw new ExternalServiceException(serviceName + " returned empty body or data");
        }
        return commonResponse.getData();
    }
}
