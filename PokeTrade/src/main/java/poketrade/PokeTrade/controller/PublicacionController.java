package poketrade.PokeTrade.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poketrade.PokeTrade.DTo.PublicacionDTo;
import poketrade.PokeTrade.model.Publicacion;
import poketrade.PokeTrade.services.PublicacionServices;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publicacion")
public class PublicacionController {

    @Autowired
    private PublicacionServices publicacionServices;

    @GetMapping
    public ResponseEntity<List<Publicacion>> listar(){
        List<Publicacion> publicacion = publicacionServices.findAll();
        return ResponseEntity.ok(publicacion);
    }

    @PostMapping
    public ResponseEntity<Publicacion> guardar(@Valid @RequestBody PublicacionDTo dto){
        Publicacion publicacion = publicacionServices.save(dto);
        return ResponseEntity.status(201).body(publicacion);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        publicacionServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
