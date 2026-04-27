package poketrade.PokeTrade.DTo;

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
    private String nombre;

    @Min(value = 1, message = "Los PS deben ser mayores a 0")
    @Max(value = 340, message = "Los PS no deben superar los 340")
    private int ps;

    @NotBlank(message = "Debe tener un tipo")
    @Size(max = 20, message = "El tipo no puede superar los 20 caracteres")
    private String tipo;

    @Size(max = 35, message = "La habilidad no puede superar los 35 caracteres")
    private String habilidad;

    @Size(max = 100, message = "La descripción de habilidad no puede superar los 100 caracteres")
    private String descripcionHabilidad;

    @Min(value = 0, message = "El coste de energía no puede ser negativo")
    @Max(value = 8, message = "El coste de energia debe ser inferior a 8")
    private int costeEnergia;

    @Min(value = 0, message = "La retirada no puede ser negativa")
    private int retirada;

    @Size(max = 35, message = "El ataque no puede superar los 35 caracteres")
    private String ataque;

    @Min(value = 0, message = "El daño del ataque no puede ser negativo")
    @Max(value = 400, message = "El daño del ataque no deben superar los 400")
    private int danoAtaque;

    @Size(max = 100, message = "La descripción del ataque no puede superar los 100 caracteres")
    private String descripcionAtaque;

    @NotBlank(message = "Debe existir una debilidad")
    private String debilidad;

    @Min(value = 0, message = "La resistencia no puede ser negativa")
    private int resistencia;

    @NotBlank(message = "Debe existir una rareza")
    private String rareza;
}
