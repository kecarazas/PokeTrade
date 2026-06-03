package poketrade.PokeTrade.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="compra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private Double total;

    @Column(name="usuario_id", nullable = false)
    private Integer usuarioId;

    @ManyToOne
    @JoinColumn(name="publicacion_id", nullable = false)
    private Publicacion publicacion;


}
