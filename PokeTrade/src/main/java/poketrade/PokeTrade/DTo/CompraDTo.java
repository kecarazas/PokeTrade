package poketrade.PokeTrade.DTo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTo {

    @NotNull(message = "Debe existir una cantidad")
    @Min(value = 1, message = "La cantidad debe ser al menos de 1")
    @Schema(description = "cantidad para comprar", examples = "1")
    private Integer cantidad;

    @NotNull(message = "Debe existir el usuario")
    @Schema(description = "username necesario para comprar", examples = "TIENDA")
    private String username;

    @NotNull(message = "Debe existir la publicacion")
    @Schema(description = "ID de la publicacion que se quiera comprar", examples = "1")
    private Integer publicacionId;
}
