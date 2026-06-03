package poketrade.PokeTrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PokeTradeApplication {
	public static void main(String[] args) {
		SpringApplication.run(PokeTradeApplication.class, args);
	}
}
