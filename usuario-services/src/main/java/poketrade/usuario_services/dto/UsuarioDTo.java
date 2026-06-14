package poketrade.usuario_services.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTo {

    @NotBlank(message = "Debe existir el nombre de usuario")
    @Size(min = 3, max = 30, message = "El username debe tener entre 3 y 30 caracteres")
    @Schema(description = "Username unico del usuario", example = "Ghost")
    private String username;

    @NotBlank(message = "Debe existir el nombre")
    @Schema(description = "Nombre del usuario", example = "Kevin")
    private String nombre;

    @NotBlank(message = "Debe existir el apellido")
    @Schema(description = "Apellido del usuario", example = "Carazas")
    private String apellido;

    @NotBlank(message = "Debe existir un correo")
    @Email(message = "El correo debe tener un formato válido")
    //Esta validacion es para ver si el correo ingresado tiene el formato de correo apropiado por ejemplo: usuario@correo.com/.cl
    @Size(max = 100, message = "El correo no puede superar los 100 caracteres")
    @Schema(description = "Correo del usuario", example = "ghost@gmail.com")
    private String email;

    @NotBlank(message = "Debe existir la contraseña")
    @Size(min = 3, max = 30, message = "La contraseña debe tener entre 3 y 30 caracteres")
    @Schema(description = "Contraseña del usuario", example = "1234Ghost")
    private String password;

}
