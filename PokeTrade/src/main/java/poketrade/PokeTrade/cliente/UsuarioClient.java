package poketrade.PokeTrade.cliente;

import poketrade.PokeTrade.DTo.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "usuario-service",
        url = "${usuario-service.url}"
)
public interface UsuarioClient {

    @GetMapping("/api/v1/usuario/{id}")
    UsuarioDTO findById(@PathVariable Integer id);

    @GetMapping("/api/v1/usuario/username/{username}")
    UsuarioDTO findByUsername(@PathVariable String username);
}
