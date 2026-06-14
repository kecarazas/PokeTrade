package poketrade.usuario_services.controller;

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
import poketrade.usuario_services.dto.UsuarioResponseDto;
import poketrade.usuario_services.dto.UsuarioDTo;
import poketrade.usuario_services.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Usuario", description = "Operaciones relacionado con los usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping
    @Operation(summary = "Obtener usuarios", description = "Obtiene una lista con todas los usuarios")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class)),
                    examples = @ExampleObject(
                            value = """
                            [
                                {
                                    "id": 1,
                                    "username": "TIENDA",
                                    "nombre": "Kevin",
                                    "apellido": "carazas",
                                    "email": "kevin@gmail.com"
                                }
                            ]
                            """
                    )
            )
    )
    public ResponseEntity<List<UsuarioResponseDto>> listar(){
        List<UsuarioResponseDto> usuario = usuarioServices.findAll();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("{id}")
    @Operation(summary = "Obtener usuario por id", description = "Obtiene los datos de un usuario a partir de su ID")
    @Parameter(name = "id", description = "ID del usuario a buscar", example = "1", in = ParameterIn.PATH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "id": 1,
                                        "username": "TIENDA",
                                        "nombre": "Kevin",
                                        "apellido": "carazas",
                                        "email": "kevin@gmail.com"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "No se encontro al usuario con el ID proporcionado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "status": 404,
                                        "error": "Not Found",
                                        "mensaje": "Usuario no encontrado"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<UsuarioResponseDto> ListarId(@PathVariable Integer id){
        UsuarioResponseDto usuario = usuarioServices.findById(id);
        return ResponseEntity.ok(usuario);
    }
    @GetMapping("/username/{username}")
    @Operation(summary = "Obtener usuario por su username", description = "Obtiene los datos de un usuario a partir de su username")
    @Parameter(name = "username", description = "Username del usuario a buscar", example = "TIENDA", in = ParameterIn.PATH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "id": 1,
                                        "username": "TIENDA",
                                        "nombre": "Kevin",
                                        "apellido": "carazas",
                                        "email": "kevin@gmail.com"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "No se encontro al usuario con el username proporcionado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "status": 404,
                                        "error": "Not Found",
                                        "mensaje": "Usuario no encontrado"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<UsuarioResponseDto> buscarPorUsername(@PathVariable String username){
        UsuarioResponseDto usuario = usuarioServices.findByUsername(username);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    @Operation(summary = "Crear un usuario", description = "Crea y persiste un nuevo usuario en el sistema a partir de los datos proporcionados")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Usuario a crear", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "id": 2,
                                        "username": "Ghost",
                                        "nombre": "Kevin",
                                        "apellido": "Carazas",
                                        "email": "ghost@gmail.com"
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
    public ResponseEntity<UsuarioResponseDto> guardar(@Valid @RequestBody UsuarioDTo dto){
        UsuarioResponseDto usuario1 = usuarioServices.save(dto);
        return ResponseEntity.status(201).body(usuario1);
    }

    @PutMapping("{id}")
    @Operation(summary = "Actualiza un usuario", description = "Actualiza al usuario en el sistema a partir de los datos proporcionados")
    @Parameter(name = "id", description = "ID del usuario a actualizar", example = "2", in = ParameterIn.PATH)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Usuario a actualizar",
            required = true,
            content =  @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioDTo.class),
                    examples = @ExampleObject(
                            value = """
                            {
                                "username": "ElBicho",
                                "nombre": "kevin",
                                "apellido": "carazas",
                                "email": "ghost@gmail.com",
                                "password": "2004ghost"
                            }
                            """
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "id": 2,
                                        "username": "ElBicho",
                                        "nombre": "Kevin",
                                        "apellido": "Carazas",
                                        "email": "ghost@gmail.com"
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
                                        "mensaje": "Debe existir el nombre de usuario"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "No se encontro al usuario con el ID proporcionado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "status": 404,
                                        "error": "Not Found",
                                        "mensaje": "Usuario no encontrado"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<UsuarioResponseDto> actualizar(@Valid @RequestBody UsuarioDTo dto, @PathVariable Integer id){
        UsuarioResponseDto usuario1 = usuarioServices.update(id, dto);
        return ResponseEntity.ok(usuario1);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar usuario por id", description = "Elimina los datos de un usuario específico a partir de su ID")
    @Parameter(name = "id", description = "ID del usuario a eliminar", example = "2", in = ParameterIn.PATH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontro al usuario con el ID proporcionado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "status": 404,
                                        "error": "Not Found",
                                        "mensaje": "Usuario no encontrado"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        usuarioServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
