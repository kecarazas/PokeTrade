package poketrade.PokeTrade.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name="carta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String nombre;

    private int ps;

    @Column(nullable=false)
    private String tipo;

    private String habilidad;

    @Column(length=100)
    private String descripcionHabilidad;

    private int costeEnergia;

    private int retirada;

    private String ataque;

    private int danoAtaque;

    @Column(length=100)
    private String descripcionAtaque;

    @Column(nullable = false)
    private String debilidad;

    private int resistencia;

    @Column(nullable=false)
    private String rareza;

}
