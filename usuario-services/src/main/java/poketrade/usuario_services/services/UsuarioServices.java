package poketrade.usuario_services.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poketrade.usuario_services.dto.UsuarioDTo;
import poketrade.usuario_services.dto.UsuarioResponseDto;
import poketrade.usuario_services.exception.NotFoundException;
import poketrade.usuario_services.mapper.UsuarioMapper;
import poketrade.usuario_services.model.Usuario;
import poketrade.usuario_services.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
@Service
public class UsuarioServices {

    // Herramienta para registrar eventos importantes mientras el sistema corre
    private static final Logger log = LoggerFactory.getLogger(UsuarioServices.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDto> findAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    public UsuarioResponseDto findById(Integer id){
        Usuario usuario =  usuarioRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Usuario no encontrado"));
        return UsuarioMapper.toDTO(usuario);
    }
    public UsuarioResponseDto findByUsername(String username){
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() ->
                        new NotFoundException("Usuario no encontrado"));
        return UsuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDto save(UsuarioDTo dto){

        // Registramos cuando se registra un nuevo usuario en el sistema
        log.info("Creando nuevo usuario: {}", dto.getUsername());
        Usuario usuario = new Usuario();

        usuario.setUsername(dto.getUsername());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());

        Usuario guardar = usuarioRepository.save(usuario);
        log.info("Usuario guardado con el id: {}", guardar.getId());
        return UsuarioMapper.toDTO(guardar);
    }

    public UsuarioResponseDto update(Integer id, UsuarioDTo dto){

        // Registramos cuando se actualiza la información de un usuario
        log.info("Actualizando usuario con id: {}", id);

        //buscamos al usuario por el id, en caso que no exista nos lanza un error 404
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No existe el usuario con el id: {}", id);
                    return new NotFoundException("Usuario no encontrado");
                });

        usuario.setUsername(dto.getUsername());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());

        Usuario guardar = usuarioRepository.save(usuario);
        log.info("Usuario guardado con el id: {}", guardar.getId());
        return UsuarioMapper.toDTO(guardar);
    }

    public void delete(Integer id){
        // Registramos cuando se elimina un usuario del sistema
        log.info("Eliminando usuario con id: {}", id);

        //condicion que nos advierte que inteno eliminar un usuario cuyo id no existe
        if(!usuarioRepository.existsById(id)){
            log.warn("Intento de eliminar usuario inexistente con el id: {}", id);
            throw new NotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
        log.info("Usuario eliminado con el id: {}", id);
    }
}