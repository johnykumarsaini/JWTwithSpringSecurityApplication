package in.jk.springboot.configuaration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
    	
    	//Adding Header
    	ParameterBuilder authorizationParameterBuilder = new ParameterBuilder();
    	authorizationParameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false).description("Enter Token ").build();
      	ParameterBuilder isauthorizationRequiredBuilder = new ParameterBuilder();
      	isauthorizationRequiredBuilder.name("IsAuthorizationRequired").modelRef(new ModelRef("string")).parameterType("header").required(true).description("Enter Y or N ").build();
    	List<Parameter> aParameters = new ArrayList<Parameter>();
    	aParameters.add(authorizationParameterBuilder.build());
    	aParameters.add(isauthorizationRequiredBuilder.build());
    	
        return new Docket(DocumentationType.SPRING_WEB)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build().pathMapping("").globalOperationParameters(aParameters);                                          
    }
}


//
////Adding Header
//ParameterBuilder aParameterBuilder = new ParameterBuilder();
//aParameterBuilder.name("headerName").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
//List<Parameter> aParameters = new ArrayList<Parameter>();
//aParameters.add(aParameterBuilder.build());
//return new Docket(DocumentationType.SWAGGER_2).select()
//        .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
//        .apiInfo(apiInfo()).pathMapping("").globalOperationParameters(aParameters);