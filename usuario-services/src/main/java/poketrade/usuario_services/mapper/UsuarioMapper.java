package poketrade.usuario_services.mapper;

import poketrade.usuario_services.dto.UsuarioResponseDto;
import poketrade.usuario_services.model.Usuario;

public class UsuarioMapper {
    public static UsuarioResponseDto toDTO(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail()
        );
    }
}
