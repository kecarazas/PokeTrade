package poketrade.PokeTrade.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poketrade.PokeTrade.DTo.CompraDTo;
import poketrade.PokeTrade.model.Compra;
import poketrade.PokeTrade.services.CompraServices;

import java.util.List;

@RestController
@RequestMapping("/api/v1/compra")
public class CompraController {
    @Autowired
    private CompraServices compraServices;

    @GetMapping
    public ResponseEntity<List<Compra>> listar(){
        List<Compra> compra = compraServices.findAllCompra();
        return ResponseEntity.ok().body(compra);
    }

    @PostMapping
    public ResponseEntity<Compra> comprarCarta(@Valid @RequestBody CompraDTo dto){
        Compra compra = compraServices.comprar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(compra);
    }
}
