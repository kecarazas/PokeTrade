package poketrade.PokeTrade.model;

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

    @Column(nullable=false)
    private Double precio;

    @Column(nullable=false)
    private Integer stock;

    @Column(nullable=false)
    private String tipoVendedor;

    @ManyToOne
    @JoinColumn(name="carta_id", nullable = false)
    private Carta carta;

    @ManyToOne
    @JoinColumn(name="usuario_id", nullable = false)
    private Usuario usuario;

}
