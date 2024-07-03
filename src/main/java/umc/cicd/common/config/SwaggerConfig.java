package umc.cicd.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI OpenApi() {
        Info info = new Info()
                .title("Open API")
                .description("API 명세서")
                .version("V1");

        String jwtSchemeName = "JWT TOKEN";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .servers(Arrays.asList(
                        new Server().url("https://dev.seungyeon.shop").description("Develop server"), new Server().url("http://localhost:8080").description("Local server")))
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }

}