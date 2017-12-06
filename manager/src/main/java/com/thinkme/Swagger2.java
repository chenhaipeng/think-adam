package com.thinkme;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enabled}")
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
//        ParameterBuilder aParameterBuilder = new ParameterBuilder();
//        aParameterBuilder.name("userId").defaultValue("").description("用户id").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//
//        List<Parameter> aParameters = new ArrayList<Parameter>();
//        aParameters.add(aParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                .globalOperationParameters(aParameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.thinkme.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("adam RESTful APIs")
                .description("adam")
                .termsOfServiceUrl("")
                .contact("chenhaipeng")
                .version("1.0")
                .build();
    }

}
