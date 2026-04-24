package poketrade.PokeTrade.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name="usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Debe existir el nombre de usuario")
    @Size(min = 3, max = 30, message = "El username debe tener entre 3 y 30 caracteres")
    @Column(nullable=false, unique=true)
    private String username;

    @NotBlank(message = "Debe existir un correo")
    @Email(message = "El correo debe tener un formato válido")
    //Esta validacion es para ver si el correo ingresado tiene el formato de correo apropiado por ejemplo: usuario@correo.com/.cl
    @Size(max = 100, message = "El correo no puede superar los 100 caracteres")
    @Column(nullable=false, unique=true)
    private String correo;
}
