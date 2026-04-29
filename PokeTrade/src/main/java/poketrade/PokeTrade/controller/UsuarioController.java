package poketrade.PokeTrade.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import poketrade.PokeTrade.model.Usuario;
import poketrade.PokeTrade.DTo.UsuarioDTo;
import poketrade.PokeTrade.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        List<Usuario> usuario = usuarioServices.findAll();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> ListarId(@PathVariable Integer id){
        Usuario usuario = usuarioServices.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardar(@Valid @RequestBody UsuarioDTo dto){
        Usuario usuario1 = usuarioServices.save(dto);
        return ResponseEntity.status(201).body(usuario1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        usuarioServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
