package poketrade.PokeTrade.controller;

import poketrade.PokeTrade.model.Carta;
import poketrade.PokeTrade.services.CartaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/cartas")
public class CartaController {

    @Autowired
    private CartaServices cartaServices;

    @GetMapping
    public List<Carta> listar(){
        return cartaServices.findAll();
    }

    @PostMapping
    public Carta guardar(@RequestBody Carta carta){
        return cartaServices.save(carta);
    }


}
