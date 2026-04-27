package poketrade.PokeTrade.DTo;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionDTo {
    @NotNull(message = "Debe existir un precio")
    @Positive(message = "El precio debe ser positivo")
    private Double precio;

    @NotNull(message = "Debe existir un stock")
    @Min(value = 1, message = "El stock debe ser al menos 1")
    private Integer stock;

    @NotBlank(message = "Debe existir un tipo de vendedor")
    @Size(min = 5, max = 25, message = "El tipo de vendedor debe tener entre 5 y 25 caracteres")
    private String tipoVendedor;

    @NotNull(message = "Debe existir la carta")
    private Integer cartaId;

    @NotNull(message = "Debe existir el usuario")
    private String username;
}
