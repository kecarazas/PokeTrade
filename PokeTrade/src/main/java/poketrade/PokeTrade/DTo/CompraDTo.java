package poketrade.PokeTrade.DTo;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTo {

    @NotNull(message = "Debe existir una cantidad")
    @Min(value = 1, message = "La cantidad debe ser al menos de 1")
    private Integer cantidad;

    @NotNull(message = "Debe existir el usuario")
    private String username;

    @NotNull(message = "Debe existir la publicacion")
    private Integer publicacionId;
}
