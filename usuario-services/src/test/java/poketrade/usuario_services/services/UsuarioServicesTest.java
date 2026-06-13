package poketrade.usuario_services.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import poketrade.usuario_services.dto.UsuarioDTo;
import poketrade.usuario_services.exception.NotFoundException;
import poketrade.usuario_services.model.Usuario;
import poketrade.usuario_services.repository.UsuarioRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServicesTest {
    @InjectMocks
    private UsuarioServices usuarioServices;
    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void deberiaGuardarUsuarioCorrectamente(){
        //given
        UsuarioDTo dto = new UsuarioDTo();
        dto.setUsername("ghost");
        dto.setNombre("kevin");
        dto.setApellido("carazas");
        dto.setEmail("ghost@gmail.com");
        dto.setPassword("1234567");

        when(usuarioRepository.save(any(Usuario.class)))
                .thenAnswer(invocation ->{
                    Usuario usuario = invocation.getArgument(0);
                    usuario.setId(1);
                    return usuario;
                });
        //when
        Usuario usuario = usuarioServices.save(dto);

        //then
        assertNotNull(usuario);
        assertEquals("ghost", usuario.getUsername());
        assertEquals("kevin", usuario.getNombre());
        assertEquals("carazas", usuario.getApellido());
        assertEquals("ghost@gmail.com", usuario.getEmail());
        assertEquals("1234567", usuario.getPassword());
    }

    @Test
    void deberiaActualizarUsuarioCorrectamente(){
        UsuarioDTo dto = new UsuarioDTo();
        dto.setUsername("ghost");
        dto.setNombre("kevin");
        dto.setApellido("carazas");

        Usuario usuario = new Usuario();
        usuario.setId(1);

        when(usuarioRepository.findById(1))
                .thenReturn(Optional.of(usuario));

        when(usuarioRepository.save(any(Usuario.class)))
                .thenAnswer(invocation ->{
                    Usuario u= invocation.getArgument(0);
                    u.setId(1);
                    return u;
                });
        //when
        Usuario user = usuarioServices.update(1, dto);

        //then
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("ghost", user.getUsername());
        assertEquals("kevin", user.getNombre());
        assertEquals("carazas", user.getApellido());

    }

    @Test
    void deberiaLanzarErrorCuandoNoEncuentraUsuarioAlActualizar(){
        //given
        UsuarioDTo dto = new UsuarioDTo();
        dto.setUsername("ghost");
        dto.setNombre("kevin");

        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        //when
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> usuarioServices.update(1, dto)
        );

        //then
        assertEquals("Usuario no encontrado", exception.getMessage());
    }

    @Test
    void deberiaEliminarUsuarioCorrectamente(){
        //given
        when(usuarioRepository.existsById(1)).thenReturn(Boolean.TRUE);

        //when
        usuarioServices.delete(1);

        //then
        verify(usuarioRepository).deleteById(1);
    }

    @Test
    void deberiaLanzarErrorCuandoIdEsInexistente(){
        //given
        when(usuarioRepository.existsById(1)).thenReturn(Boolean.FALSE);

        //when
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> usuarioServices.delete(1)
        );

        //then
        assertEquals("Usuario no encontrado", exception.getMessage());
    }
}
