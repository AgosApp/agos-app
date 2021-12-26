package UFC.Agos.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API for AGOS App")
                .description("a Swagger Documentation for Agos Api ")
                .contact(new Contact("Agos team", "","mourtada.boufelja@edu.univ.fcomte.fr"))
                .version("1.0")
                .build();
    }
}
