package poketrade.PokeTrade.controller;

import org.springframework.http.ResponseEntity;
import poketrade.PokeTrade.model.Carta;
import poketrade.PokeTrade.services.CartaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/carta")
public class CartaController {

    @Autowired
    private CartaServices cartaServices;

    @GetMapping
    public ResponseEntity<List<Carta>> listar(){
        List<Carta> carta = cartaServices.findAll();
        return ResponseEntity.ok(carta);
    }

    @PostMapping
    public ResponseEntity<Carta> guardar(@RequestBody Carta carta){
        Carta  carta1 = cartaServices.save(carta);
        return ResponseEntity.status(201).body(carta1);
    }

    //metodo para guardar una lista con las cartas en la base de datos
    @PostMapping("/lista")
    public ResponseEntity<List<Carta>> guardarLista(@RequestBody List<Carta> carta){
        List<Carta> carta1 = cartaServices.saveLista(carta);
        return ResponseEntity.status(201).body(carta1);
    }

    @GetMapping("{id}")
    public ResponseEntity<Carta> listarId(@PathVariable Integer id){
        Carta carta1  = cartaServices.findById(id);
        return ResponseEntity.ok(carta1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        cartaServices.delete(id);
        return ResponseEntity.ok("carta elimanada correctamente");
    }

}
