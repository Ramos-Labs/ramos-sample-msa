package com.inje.reviewservice.config.openapi;

import com.inje.reviewservice.config.openapi.annotation.ApiErrorResponse;
import com.inje.reviewservice.config.openapi.annotation.ApiErrorResponses;
import com.inje.reviewservice.support.enumeration.ResponseTypeCodeEnum;
import com.inje.reviewservice.support.response.CommonResponse;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

/**
 * Open API (Swagger Doc) Configuration Bean.
 *
 * @author HakHyeon Song
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .version("v1.0")
                .title("Review Service API")
                .description("MSA Demo Application - Review Service API");

        return new OpenAPI()
                .info(info);
    }

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            ApiErrorResponses apiErrorResponses = handlerMethod.getMethodAnnotation(ApiErrorResponses.class);

            // @ApiErrorResponses 어노테이션이 붙어있을 때, 예외 응답 예시를 추가.
            if (apiErrorResponses != null) {
                generateErrorResponses(operation, apiErrorResponses.value());

                return operation;
            }

            // @ApiErrorResponse 어노테이션이 붙어있을 때, 예외 응답 예시를 추가.
            ApiErrorResponse apiErrorResponse = handlerMethod.getMethodAnnotation(ApiErrorResponse.class);
            if (apiErrorResponse != null) {
                generateErrorResponses(operation, new ApiErrorResponse[] {apiErrorResponse});

                return operation;
            }
            return operation;
        };
    }

    // api 예외 응답 추가
    private void generateErrorResponses(Operation operation, ApiErrorResponse[] apiErrorResponses) {
        // key : httpStatusCode, Value : ExampleHolder 리스트
        Map<Integer, List<ExampleHolder>> exampleHolderMapByStatusCode = Arrays.stream(apiErrorResponses)
                .map(apiErrorResponse -> {
                    ResponseTypeCodeEnum responseType = apiErrorResponse.responseType();
                    Example swaggerExample = createExample(responseType);

                    return new ExampleHolder(swaggerExample, responseType.getHttpStatus().value(), apiErrorResponse.description());
                })
                .collect(Collectors.groupingBy(ExampleHolder::getHttpStatusCode));

        // ApiResponses 에 예외 응답 추가
        ApiResponses responses = operation.getResponses();
        addExamplesToResponses(responses, exampleHolderMapByStatusCode);
    }

    // ResponseTypeCodeEnum 에 따라 예외 응답 예시 생성
    private Example createExample(ResponseTypeCodeEnum responseTypeCodeEnum) {
        CommonResponse<Object> errorResponse = CommonResponse.error(responseTypeCodeEnum);

        Example example = new Example();
        example.setValue(errorResponse);

        return example;
    }

    // ApiResponses 에 예외 응답 추가
    private void addExamplesToResponses(ApiResponses responses, Map<Integer, List<ExampleHolder>> exampleHolderMapByStatusCode) {
        exampleHolderMapByStatusCode.forEach(
                (statusCode, exampleHolderList) -> {
                    Content content = new Content();
                    MediaType mediaType = new MediaType();
                    ApiResponse apiResponse = new ApiResponse();

                    exampleHolderList.forEach(
                            exampleHolder -> {
                                mediaType.addExamples(
                                        exampleHolder.getDescription(),
                                        exampleHolder.getExample()
                                );
                                // Schema 를 CommonResponse Schema 로 설정
                                mediaType.setSchema(new Schema<>().$ref("CommonResponse"));
                            }
                    );
                    content.addMediaType("*/*", mediaType);
                    apiResponse.setContent(content);

                    responses.addApiResponse(String.valueOf(statusCode), apiResponse);
                }
        );
    }

    @Getter
    @AllArgsConstructor
    private static class ExampleHolder {
        private Example example;
        private Integer httpStatusCode;
        private String description;
    }
}
