package poketrade.PokeTrade.DTo;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer id;
    private String username;
    private String nombre;
    private String apellido;
    private String email;
}
