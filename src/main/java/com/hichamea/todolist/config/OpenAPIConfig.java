package com.hichamea.todolist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${todolist.openapi.dev-url}")
    private String devUrl;

    @Value("${todolist.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {

        Server devServer = createServer(devUrl, "Server URL in Development environment");
        Server prodServer = createServer(prodUrl, "Server URL in Production environment");

        return new OpenAPI().info(new Info().title("ToDo List API")
                                            .description("ToDo List management API")
                                            .version("1.0")).servers(List.of(devServer, prodServer));
    }

    private Server createServer(String url, String description) {
        return new Server().url(url).description(description);
    }
}
