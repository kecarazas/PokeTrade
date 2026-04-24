package poketrade.PokeTrade.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name="publicacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Debe existir un precio")
    @Positive(message = "El precio debe ser positivo")
    @Column(nullable=false)
    private Double precio;

    @NotNull(message = "Debe existir un stock")
    @Min(value = 1, message = "El stock debe ser al menos 1")
    @Column(nullable=false)
    private Integer stock;

    @NotBlank(message = "Debe existir un tipo de vendedor")
    @Size(min = 5, max = 25, message = "El tipo de vendedor debe tener entre 5 y 25 caracteres")
    @Column(nullable=false)
    private String tipoVendedor;

    @NotNull(message = "Debe existir la carta")
    @ManyToOne
    @JoinColumn(name="carta_id", nullable = false)
    private Carta carta;

    @NotNull(message = "Debe existir el usuario")
    @ManyToOne
    @JoinColumn(name="usuario_id", nullable = false)
    private Usuario usuario;

}
