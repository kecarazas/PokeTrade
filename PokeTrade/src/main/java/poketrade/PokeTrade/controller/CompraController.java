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
@Tag(name = "Compra", description = "Operaciones relacionada con las compras de cartas")
public class CompraController {
    @Autowired
    private CompraServices compraServices;

    @GetMapping
    @Operation(summary = "Obtener todas la compras", description = "Obtiene una lista con todas las compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de compras obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation =  Compra.class)),
                            examples = @ExampleObject(
                                    value = """
                                    [
                                        {
                                            "id": 1,
                                            "cantidad": 1,
                                            "total": 39.999,
                                            "usuarioId": 1,
                                            "publicacion": {
                                                "id": 1,
                                                "precio": 39.999,
                                                "stock": 9,
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
                                        }
                                    ]
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<List<Compra>> listar(){
        List<Compra> compra = compraServices.findAllCompra();
        return ResponseEntity.ok().body(compra);
    }

    @PostMapping
    @Operation(summary = "Comprar un carta", description = "Compra una carta mediante una publicacion de otro usuario")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Comprar una carta", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Compra realizada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Compra.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "id": 1,
                                        "cantidad": 1,
                                        "total": 39.999,
                                        "usuarioId": 3,
                                        "publicacion": {
                                            "id": 1,
                                            "precio": 39.999,
                                            "stock": 8,
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
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "La publicación o el usuario no existen",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                @ExampleObject(
                                        name = "No existe la publicacion",
                                        summary = "No se encontro la publicacion con el ID proporcionado",
                                        value = """
                                        {
                                            "status": 404,
                                            "error": "Not Found",
                                            "mensaje": "Publicacion no encontrada"
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
            @ApiResponse(responseCode = "409", description = "Conflicto con el stok de la publicacion",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Sin stock",
                                            summary = "No hay stock disponible",
                                            value = """
                                        {
                                            "status": 404,
                                            "error": "Not Found",
                                            "mensaje": "No hay stock disponible"
                                        }
                                        """
                                    ),
                                    @ExampleObject(
                                            name = "Stock insuficiente",
                                            summary = "No hay suficiente stock",
                                            value = """
                                        {
                                            "status": 404,
                                            "error": "Not Found",
                                            "mensaje": "No hay cantidad disponible"
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
    public ResponseEntity<Compra> comprarCarta(@Valid @RequestBody CompraDTo dto){
        Compra compra = compraServices.comprar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(compra);
    }
}
