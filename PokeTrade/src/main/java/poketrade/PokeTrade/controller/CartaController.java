package poketrade.PokeTrade.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import poketrade.PokeTrade.DTo.CartaListaDTO;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cartas obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Carta.class)),
                            examples = @ExampleObject(
                                    value = """
                                    [
                                        {
                                            "id": 1,
                                            "nombre": "Bulbasaur",
                                            "ps": 70,
                                            "tipo": "Planta",
                                            "habilidad": "Clorofila",
                                            "descripcionHabilidad": "Aumenta su poder natural",
                                            "costeEnergia": 1,
                                            "retirada": 1,
                                            "ataque": "Latigo Cepa",
                                            "danoAtaque": 40,
                                            "descripcionAtaque": "Ataque de tipo planta",
                                            "debilidad": "Fuego",
                                            "resistencia": 10,
                                            "rareza": "Comun"
                                        },
                                        {
                                            "id": 2,
                                            "nombre": "Ivysaur",
                                            "ps": 70,
                                            "tipo": "Planta",
                                            "habilidad": "Clorofila",
                                            "descripcionHabilidad": "Aumenta su poder natural",
                                            "costeEnergia": 1,
                                            "retirada": 1,
                                            "ataque": "Latigo Cepa",
                                            "danoAtaque": 40,
                                            "descripcionAtaque": "Ataque de tipo planta",
                                            "debilidad": "Fuego",
                                            "resistencia": 10,
                                            "rareza": "Comun"
                                        }
                                    ]
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<List<Carta>> listar(){
        List<Carta> carta = cartaServices.findAll();
        return ResponseEntity.ok(carta);
    }

    @GetMapping("{id}")
    @Operation(summary = "Obtener carta por id", description = "Obtiene los datos de una carta específica a partir de su identificador único")
    @Parameter(name = "id", description = "ID de la carta a buscar", example = "1", in = ParameterIn.PATH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carta encontrada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Carta.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "id": 1,
                                        "nombre": "Bulbasaur",
                                        "ps": 70,
                                        "tipo": "Planta",
                                        "habilidad": "Clorofila",
                                        "descripcionHabilidad": "Aumenta su poder natural",
                                        "costeEnergia": 1,
                                        "retirada": 1,
                                        "ataque": "Latigo Cepa",
                                        "danoAtaque": 40,
                                        "descripcionAtaque": "Ataque de tipo planta",
                                        "debilidad": "Fuego",
                                        "resistencia": 10,
                                        "rareza": "Comun"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "No se encontro la carta con el ID proporcionado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "status": 404,
                                        "error": "Not Found",
                                        "mensaje": "No se encontró la carta con id 200"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<Carta> listarId(@PathVariable Integer id){
        Carta carta1  = cartaServices.findById(id);
        return ResponseEntity.ok(carta1);
    }

    @PostMapping
    @Operation(summary = "Guardar una carta", description = "Crea y persiste una nueva carta en el sistema a partir de los datos proporcionados")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Carta a crear", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carta guardada correctamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Carta.class),
                    examples = @ExampleObject(
                        value = """
                        {
                            "id": 1,
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
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Lista de cartas a crear", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cartas guardadas correctamente",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Carta.class)),
                            examples = @ExampleObject(
                                    value = """
                                    [
                                        {
                                            "id" : 195,
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
                            examples = {
                                    @ExampleObject(
                                            name = "campo invalido",
                                            summary = "Falta un campo obligatorio o no cumple las restricciones",
                                            value = """
                                            {
                                                "status": 400,
                                                "error": "Bad Request",
                                                "mensaje": "Debe existir un nombre"
                                            }
                                            """
                                    ),
                                    @ExampleObject(
                                            name = "Lista vacia",
                                            summary = "La lista de cartas no contiene elementos",
                                            value = """
                                            {
                                                "status": 400,
                                                "error": "Bad Request",
                                                "mensaje": "La lista de cartas no puede estar vacía"
                                            }
                                            """
                                   )
                            }
                    )
            )
    })
    public ResponseEntity<List<Carta>> guardarLista(@Valid @RequestBody CartaListaDTO dto){
        List<Carta> carta1 = cartaServices.saveLista(dto.getListaCartas());
        return ResponseEntity.status(201).body(carta1);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar carta por id", description = "Elimina los datos de una carta específica a partir de su identificador único")
    @Parameter(name = "id", description = "ID de la carta a eliminar", example = "1", in = ParameterIn.PATH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carta eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontro la carta con el ID proporcionado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "status": 404,
                                        "error": "Not Found",
                                        "mensaje": "carta no encontrada"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        cartaServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
