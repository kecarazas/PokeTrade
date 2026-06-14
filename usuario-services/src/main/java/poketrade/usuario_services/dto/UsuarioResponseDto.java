package poketrade.usuario_services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto {
    private Integer id;
    private String username;
    private String nombre;
    private String apellido;
    private String email;
}
