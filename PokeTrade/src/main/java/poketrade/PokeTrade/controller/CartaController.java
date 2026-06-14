package poketrade.PokeTrade.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import poketrade.PokeTrade.DTo.CartaDTo;
import poketrade.PokeTrade.model.Carta;
import poketrade.PokeTrade.services.CartaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/carta")
@Tag(name = "Carta", description = "Operaciones relacionada con las cartas")
public class CartaController {

    @Autowired
    private CartaServices cartaServices;

    @GetMapping
    @Operation(summary = "Obtener todas las cartas", description = "Obtiene una lista con todas las cartas")

    public ResponseEntity<List<Carta>> listar(){
        List<Carta> carta = cartaServices.findAll();
        return ResponseEntity.ok(carta);
    }

    @PostMapping
    @Operation(summary = "Guardar una carta", description = "Crea y persiste una nueva carta en el sistema a partir de los datos proporcionados")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Carta a crear", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carta guardada correctamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CartaDTo.class),
                    examples = @ExampleObject(
                        value = """
                        {
                            "nombre": "Charizard",
                            "ps": 120,
                            "tipo": "Fuego",
                            "habilidad": "Mar de Llamas",
                            "descripcionHabilidad": "Aumenta el daño de fuego",
                            "costeEnergia": 2,
                            "retirada": 2,
                            "ataque": "Lanzallamas",
                            "danoAtaque": 80,
                            "descripcionAtaque": "Ataque de tipo fuego",
                            "debilidad": "Agua",
                            "resistencia": 20,
                            "rareza": "Rara"
                        }
                        """
                    )
                )
            ),
            @ApiResponse(responseCode = "400", description = "Datos invalidos o incompletos",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "status": 400,
                                        "error": "Bad Request",
                                        "mensaje": "Debe existir un nombre"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<Carta> guardar(@Valid @RequestBody CartaDTo dto){
        Carta  carta1 = cartaServices.save(dto);
        return ResponseEntity.status(201).body(carta1);
    }

    //endpoint para guardar una lista de cartas en la base de datos
    @PostMapping("/lista")
    @Operation(summary = "Guardar una lista de cartas", description = "Crea y persiste una lista de cartas en el sistema a partir de los datos proporcionados")
    @ArraySchema(schema = @Schema(implementation = CartaDTo.class))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Lista de cartas a crear", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cartas guardadas correctamente",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                    [
                                        {
                                            "nombre": "Charizard",
                                            "ps": 120,
                                            "tipo": "Fuego",
                                            "habilidad": "Mar de Llamas",
                                            "descripcionHabilidad": "Aumenta el daño de fuego",
                                            "costeEnergia": 2,
                                            "retirada": 2,
                                            "ataque": "Lanzallamas",
                                            "danoAtaque": 80,
                                            "descripcionAtaque": "Ataque de tipo fuego",
                                            "debilidad": "Agua",
                                            "resistencia": 20,
                                            "rareza": "Rara"
                                        }
                                    ]
                                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Datos invalidos o incompletos",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "status": 400,
                                        "error": "Bad Request",
                                        "mensaje": "Debe existir un nombre"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<List<Carta>> guardarLista(@Valid @RequestBody List<CartaDTo> dto){
        List<Carta> carta1 = cartaServices.saveLista(dto);
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
        return ResponseEntity.noContent().build();
    }

}
