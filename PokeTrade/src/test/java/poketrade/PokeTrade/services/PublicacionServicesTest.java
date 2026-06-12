package poketrade.PokeTrade.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import poketrade.PokeTrade.DTo.PublicacionDTo;
import poketrade.PokeTrade.DTo.UsuarioDTO;
import poketrade.PokeTrade.cliente.UsuarioClient;
import poketrade.PokeTrade.exception.NotFoundException;
import poketrade.PokeTrade.exception.RemoteServiceException;
import poketrade.PokeTrade.model.Carta;
import poketrade.PokeTrade.model.Publicacion;
import poketrade.PokeTrade.repository.CartaRepository;
import poketrade.PokeTrade.repository.PublicacionRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PublicacionServicesTest {

    @InjectMocks
    private PublicacionServices publicacionServices;

    @Mock
    private PublicacionRepository publicacionRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private CartaRepository cartaRepository;

    @Test
    void DeberiaCrearPublicacionCorrectamente(){
        //Given
        PublicacionDTo dto = new PublicacionDTo();
        dto.setPrecio(10000.0);
        dto.setStock(5);
        dto.setTipoVendedor("USUARIO");
        dto.setCartaId(1);
        dto.setUsername("kevin");

        Carta carta = new Carta();
        carta.setId(5);

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1);

        when(cartaRepository.findById(1))
                .thenReturn(Optional.of(carta));

        when(usuarioClient.findByUsername("kevin"))
                .thenReturn(usuario);
        when(publicacionRepository.save(any(Publicacion.class)))
                .thenAnswer(invocation -> {
                    Publicacion p =  invocation.getArgument(0);
                    p.setId(1);
                    return p;
                });

        //when
        Publicacion publicacion = publicacionServices.save(dto);

        //then
        assertNotNull(publicacion);
        assertEquals(5, publicacion.getStock());
        assertEquals(10000.0, publicacion.getPrecio());
        assertEquals(1, publicacion.getUsuarioId());
    }
    @Test
    void deberiaLanzarErrorCuandoNoExisteUnaCarta(){
        //given
        PublicacionDTo dto = new PublicacionDTo();
        dto.setCartaId(99);

        when(cartaRepository.findById(99))
                .thenReturn(Optional.empty());

        //when-then
        assertThrows(
                NotFoundException.class,
                () -> publicacionServices.save(dto)
        );
    }
    @Test
    void deberiaLanzarErrorCuandoNoEncuentraAlUsuario(){
        //given
        PublicacionDTo dto = new PublicacionDTo();
        dto.setCartaId(1);
        dto.setUsername("ghost");

        Carta carta = new Carta();
        carta.setId(1);

        when(cartaRepository.findById(1))
                .thenReturn(Optional.of(carta));

        when(usuarioClient.findByUsername("ghost"))
                .thenReturn(null);
        //when
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                ()-> publicacionServices.save(dto)
        );
        //then
        assertEquals("Usuario no encontrado", exception.getMessage());
    }

    @Test
    void deberiaLanzarErrorCuandoElServicioUsuarioNoFunciona(){
        //given
        PublicacionDTo dto = new PublicacionDTo();
        dto.setCartaId(1);
        dto.setUsername("ghost");

        Carta carta = new Carta();
        carta.setId(1);

        when(cartaRepository.findById(1)).thenReturn(Optional.of(carta));

        when(usuarioClient.findByUsername("ghost"))
                .thenThrow(new RuntimeException("servicio caido"));

        //when
        RemoteServiceException exception = assertThrows(
                RemoteServiceException.class,
                () -> publicacionServices.save(dto)
        );
        //then
        assertEquals("No fue posible comunicarse con el servicio usuario", exception.getMessage());
    }

    @Test
    void deberiaElimiarLaPublicacionCorrectamente(){
        //given
        when(publicacionRepository.existsById(1)).thenReturn(Boolean.TRUE);

        //when
        publicacionServices.delete(1);

        //then
        verify(publicacionRepository).deleteById(1);
    }
    @Test
    void deberiaDarErrorCuandoNoExisteElIdDeLaPublicacion(){
        //given
        when(publicacionRepository.existsById(99)).thenReturn(Boolean.FALSE);

        //when
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> publicacionServices.delete(99)
        );

        //then
        assertEquals("Publicacion no encontrada", exception.getMessage());
    }
}
