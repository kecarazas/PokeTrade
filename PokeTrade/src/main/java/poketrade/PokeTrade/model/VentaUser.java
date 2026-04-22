package poketrade.PokeTrade.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name="venta_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double precio;
    private String estado;

    @ManyToOne
    @JoinColumn(name="carta_id", nullable = false)
    private Carta carta;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

}
