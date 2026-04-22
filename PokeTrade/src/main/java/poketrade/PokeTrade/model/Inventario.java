package poketrade.PokeTrade.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;


@Entity
@Table(name="Inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private double precio;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name="carta_id", nullable = false)
    private Carta carta;
}
