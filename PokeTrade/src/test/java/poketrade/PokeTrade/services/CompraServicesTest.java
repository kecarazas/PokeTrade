package poketrade.PokeTrade.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import poketrade.PokeTrade.DTo.CompraDTo;
import poketrade.PokeTrade.DTo.UsuarioDTO;
import poketrade.PokeTrade.cliente.UsuarioClient;
import poketrade.PokeTrade.exception.NotFoundException;
import poketrade.PokeTrade.model.Compra;
import poketrade.PokeTrade.model.Publicacion;
import poketrade.PokeTrade.repository.CompraRepository;
import poketrade.PokeTrade.repository.PublicacionRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompraServicesTest {

    @InjectMocks
    private CompraServices compraServices;

    @Mock
    private CompraRepository compraRepository;

    @Mock
    private PublicacionRepository publicacionRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @Test
    void deberiaRealizarCompraCorrectamente() {

        // Given
        CompraDTo dto = new CompraDTo();
        dto.setPublicacionId(1);
        dto.setCantidad(2);
        dto.setUsername("kevin");

        Publicacion publicacion = new Publicacion();
        publicacion.setId(1);
        publicacion.setStock(10);
        publicacion.setPrecio(1000.0);

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(5);

        when(publicacionRepository.findById(1))
                .thenReturn(Optional.of(publicacion));

        when(usuarioClient.findByUsername("kevin"))
                .thenReturn(usuario);

        when(compraRepository.save(any(Compra.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Compra compra = compraServices.comprar(dto);

        // Then
        assertNotNull(compra);
        assertEquals(2, compra.getCantidad());
        assertEquals(2000, compra.getTotal());
        assertEquals(8, publicacion.getStock());
    }
    @Test
    void deberiaLanzarErrorCuandoNoExisteLaPublicacion() {

        // Given
        CompraDTo dto = new CompraDTo();
        dto.setPublicacionId(99);

        when(publicacionRepository.findById(99))
                .thenReturn(Optional.empty());

        // When - Then
        assertThrows(
                NotFoundException.class,
                () -> compraServices.comprar(dto)
        );
    }
    @Test
    void deberiaLanzarErrorCuandoNoHayStock() {

        // Given
        CompraDTo dto = new CompraDTo();
        dto.setPublicacionId(1);
        dto.setCantidad(1);

        Publicacion publicacion = new Publicacion();
        publicacion.setId(1);
        publicacion.setStock(0);

        when(publicacionRepository.findById(1))
                .thenReturn(Optional.of(publicacion));

        // When - Then
        assertThrows(
                NotFoundException.class,
                () -> compraServices.comprar(dto)
        );
    }
    @Test
    void deberiaLanzarErrorCuandoNoHayCantidadDisponible() {

        // Given
        CompraDTo dto = new CompraDTo();
        dto.setPublicacionId(1);
        dto.setCantidad(10);

        Publicacion publicacion = new Publicacion();
        publicacion.setId(1);
        publicacion.setStock(2);

        when(publicacionRepository.findById(1))
                .thenReturn(Optional.of(publicacion));

        // When - Then
        assertThrows(
                NotFoundException.class,
                () -> compraServices.comprar(dto)
        );
    }
}
