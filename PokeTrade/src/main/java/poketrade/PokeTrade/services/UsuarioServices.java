package poketrade.PokeTrade.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poketrade.PokeTrade.DTo.UsuarioDTo;
import poketrade.PokeTrade.exception.NotFoundException;
import poketrade.PokeTrade.model.Usuario;
import poketrade.PokeTrade.repository.UsuarioRepository;
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

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario save(UsuarioDTo dto){

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
        return guardar;
    }

    public Usuario update(Integer id, UsuarioDTo dto){

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
        return guardar;
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