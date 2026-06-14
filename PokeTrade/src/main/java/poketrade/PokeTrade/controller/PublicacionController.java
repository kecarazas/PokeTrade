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
import org.springdoc.core.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poketrade.PokeTrade.DTo.PublicacionDTo;
import poketrade.PokeTrade.model.Publicacion;
import poketrade.PokeTrade.services.PublicacionServices;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publicacion")
@Tag(name = "Publicacion", description = "Operaciones relacionada con las publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionServices publicacionServices;
    private GenericResponseService responseBuilder;

    @GetMapping
    @Operation(summary = "Obtener publicaciones", description = "Obtiene una lista con todas las publicaciones")
    @ApiResponse(responseCode = "200", description = "Lista de publicaciones obtenida correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Publicacion.class)),
                    examples = @ExampleObject(
                            value = """
                            [
                                {
                                    "id": 1,
                                    "precio": 39.999,
                                    "stock": 10,
                                    "tipoVendedor": "TIENDA",
                                    "carta": {
                                        "id": 6,
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
                                    },
                                    "usuarioId": 1
                                }
                            ]
                            """
                    )
            )
    )
    public ResponseEntity<List<Publicacion>> listar(){
        List<Publicacion> publicacion = publicacionServices.findAll();
        return ResponseEntity.ok(publicacion);
    }

    @PostMapping
    @Operation(summary = "Crear una publicacion", description = "Crea una publicacion para vender una carta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publicacion creada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Publicacion.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "id": 4,
                                        "precio": 19900.0,
                                        "stock": 5,
                                        "tipoVendedor": "USUARIO",
                                        "carta": {
                                            "id": 45,
                                            "nombre": "Vileplume",
                                            "ps": 120,
                                            "tipo": "Planta",
                                            "habilidad": "Clorofila",
                                            "descripcionHabilidad": "Aumenta su poder natural",
                                            "costeEnergia": 2,
                                            "retirada": 2,
                                            "ataque": "Latigo Cepa",
                                            "danoAtaque": 80,
                                            "descripcionAtaque": "Ataque de tipo planta",
                                            "debilidad": "Fuego",
                                            "resistencia": 20,
                                            "rareza": "Rara"
                                        },
                                        "usuarioId": 2
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "La carta o el usuario no existen",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "No existe la carta",
                                            summary = "No se encontro la carta con el ID proporcionado",
                                            value = """
                                            {
                                                "status": 404,
                                                "error": "Not Found",
                                                "mensaje": "Carta no encontrada"
                                            }
                                            """
                                    ),
                                    @ExampleObject(
                                            name = "No existe el usuario",
                                            summary = "No se encontro al usuario con el ID proporcionado",
                                            value = """
                                            {
                                                "status": 404,
                                                "error": "Not Found",
                                                "mensaje": "Usuario no encontrado"
                                            }
                                            """
                                    )
                            }

                    )
            ),
            @ApiResponse(responseCode = "503", description = "Servicio no disponible",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "status": 503,
                                        "error": "Servicio no disponible",
                                        "mensaje": "No fue posible comunicarse con el servicio usuario"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<Publicacion> guardar(@Valid @RequestBody PublicacionDTo dto){
        Publicacion publicacion = publicacionServices.save(dto);
        return ResponseEntity.status(201).body(publicacion);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar publicacion por id", description = "Elimina los datos de una publicacion específica a partir de su ID")
    @Parameter(name = "id", description = "ID de la publicacion a eliminar", example = "1", in = ParameterIn.PATH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Publicacion eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontro la publicacion con el ID proporcionado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "status": 404,
                                        "error": "Not Found",
                                        "mensaje": "Publicacion no encontrada"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        publicacionServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
