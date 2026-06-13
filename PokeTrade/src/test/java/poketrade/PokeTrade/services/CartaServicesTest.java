package poketrade.PokeTrade.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import poketrade.PokeTrade.DTo.CartaDTo;
import poketrade.PokeTrade.exception.NotFoundException;
import poketrade.PokeTrade.model.Carta;
import poketrade.PokeTrade.repository.CartaRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartaServicesTest {
    @InjectMocks
    private CartaServices cartaServices;

    @Mock
    private CartaRepository cartaRepository;

    @Test
    void deberiaGuardarCartaCorrectamente(){
        //given
        CartaDTo dto = new CartaDTo();
        dto.setNombre("pikachu");
        dto.setPs(100);
        dto.setAtaque("impactrueno");

        when(cartaRepository.save(any(Carta.class)))
                .thenAnswer(invocation -> {
                    Carta c = invocation.getArgument(0);
                    c.setId(1);
                    return c;
                });

        //when
        Carta carta = cartaServices.save(dto);

        //then
        assertNotNull(carta);
        assertEquals("pikachu", carta.getNombre());
        assertEquals(100, carta.getPs());
        assertEquals("impactrueno", carta.getAtaque());

    }

    @Test
    void deberiaGuardarUnaListaDeCartasCorrectamente(){
        //given
        CartaDTo dto1 = new CartaDTo();
        dto1.setNombre("pikachu");

        CartaDTo dto2 = new CartaDTo();
        dto2.setNombre("charmander");

        List<CartaDTo> lista = List.of(dto1, dto2);

        when(cartaRepository.saveAll(any())).thenAnswer(i -> i.getArgument(0));

        //when
        List<Carta> resultado = cartaServices.saveLista(lista);

        //then
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    void deberiaEliminarCartaCorrectamente(){
        //given
        when(cartaRepository.existsById(1)).thenReturn(true);

        //when
        cartaServices.delete(1);

        //then
        verify(cartaRepository).deleteById(1);
    }

    @Test
    void deberiaDarErrorCuandoNoExisteLaCartaAlEliminar(){
        //given
        when(cartaRepository.existsById(1)).thenReturn(false);

        //when
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> cartaServices.delete(1)
        );

        //then
        assertEquals("carta no encontrada", exception.getMessage());

    }
}
