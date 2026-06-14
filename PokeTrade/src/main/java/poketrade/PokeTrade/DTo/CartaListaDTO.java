package poketrade.PokeTrade.DTo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CartaListaDTO {
    @NotEmpty
    @Valid
    public List<CartaDTo> listaCartas;
}
