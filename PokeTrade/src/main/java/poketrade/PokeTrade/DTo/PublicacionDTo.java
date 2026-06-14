package poketrade.PokeTrade.DTo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionDTo {
    @NotNull(message = "Debe existir un precio")
    @Positive(message = "El precio debe ser positivo")
    @Schema(description = "Precio de la carta publicada", example = "19900.0")
    private Double precio;

    @NotNull(message = "Debe existir un stock")
    @Min(value = 1, message = "El stock debe ser al menos 1")
    @Schema(description = "Cantidad de cartas disponibles para su venta", example = "5")
    private Integer stock;

    @NotBlank(message = "Debe existir un tipo de vendedor")
    @Size(min = 5, max = 25, message = "El tipo de vendedor debe tener entre 5 y 25 caracteres")
    @Schema(description = "Quien hace la publicacion la TIENDA o el USUARIO", example = "USUARIO")
    private String tipoVendedor;

    @NotNull(message = "Debe existir la carta")
    @Schema(description = "ID de la carta que se va a vender", example = "45")
    private Integer cartaId;

    @NotNull(message = "Debe existir el usuario")
    @Schema(description = "Username unico del usuario", example = "Ghost")
    private String username;
}
