package poketrade.PokeTrade.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;


@Configuration
public class SwaggerConfig {

    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PokeTrade API")
                        .version("1.0")
                        .description("Documentacion de la API para el sistema de compra, venta y tradeo de cartas pokemon"));
    }

    @Bean
    @Profile("dev")
    public OpenAPI devOpenAPI() {
        return baseOpenAPI();
    }

    @Bean
    @Profile("prod")
    public OpenAPI prodOpenAPI() {
        return baseOpenAPI()
                .servers(List.of(
                        new Server().url("https://poketrade-production.up.railway.app").description("Servidor de produccion")
                ));
    }
}
