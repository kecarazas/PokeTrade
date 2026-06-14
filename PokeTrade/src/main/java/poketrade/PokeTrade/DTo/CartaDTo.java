package poketrade.PokeTrade.DTo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaDTo {

    @NotBlank(message = "Debe existir un nombre")
    @Size(max = 25, message = "El nombre no puede superar los 25 caracteres")
    @Schema(description = "Nombre de la carta", example = "Charizard")
    private String nombre;

    @Min(value = 1, message = "Los PS deben ser mayores a 0")
    @Max(value = 340, message = "Los PS no deben superar los 340")
    @Schema(description = "PS de la carta", example = "120")
    private int ps;

    @NotBlank(message = "Debe tener un tipo")
    @Size(max = 20, message = "El tipo no puede superar los 20 caracteres")
    @Schema(description = "Tipo de la carta", example = "Fuego" )
    private String tipo;

    @Size(max = 35, message = "La habilidad no puede superar los 35 caracteres")
    @Schema(description = "habilidad de la carta", example = "Mar de Llamas")
    private String habilidad;

    @Size(max = 100, message = "La descripción de habilidad no puede superar los 100 caracteres")
    @Schema(description = "descripcion de la habilidad", example = "Aumenta el daño de fuego")
    private String descripcionHabilidad;

    @Min(value = 0, message = "El coste de energía no puede ser negativo")
    @Max(value = 8, message = "El coste de energia debe ser inferior a 8")
    @Schema(description = "coste de enegia de la carta", example = "2")
    private int costeEnergia;

    @Min(value = 0, message = "La retirada no puede ser negativa")
    @Schema(description = "coste de retirada de la carta", example = "2")
    private int retirada;

    @Size(max = 35, message = "El ataque no puede superar los 35 caracteres")
    @Schema(description = "ataque de la carta", example = "Lanzallamas")
    private String ataque;

    @Min(value = 0, message = "El daño del ataque no puede ser negativo")
    @Max(value = 400, message = "El daño del ataque no deben superar los 400")
    @Schema(description = "daño de ataque de la carta", example = "80")
    private int danoAtaque;

    @Size(max = 100, message = "La descripción del ataque no puede superar los 100 caracteres")
    @Schema(description = "descripcion del ataque", example = "Ataque de tipo fuego")
    private String descripcionAtaque;

    @NotBlank(message = "Debe existir una debilidad")
    @Schema(description = "debilidad de la carta", example = "Agua")
    private String debilidad;

    @Min(value = 0, message = "La resistencia no puede ser negativa")
    @Schema(description = "resistencia de la carta", example = "20")
    private int resistencia;

    @NotBlank(message = "Debe existir una rareza")
    @Schema(description = "rareza de la carta", example = "rara")
    private String rareza;
}
