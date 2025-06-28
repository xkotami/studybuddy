package kotami.studybuddy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI studyBuddyOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("StudyBuddy API")
                        .version("1.0")
                        .description("REST endpoints for managing Users, Buddies, and Items"));
    }
}