package poketrade.PokeTrade.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poketrade.PokeTrade.DTo.PublicacionDTo;
import poketrade.PokeTrade.exception.NotFoundException;
import poketrade.PokeTrade.model.*;
import poketrade.PokeTrade.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class PublicacionServices {

    // Herramienta para registrar eventos importantes mientras el sistema corre
    private static final Logger log = LoggerFactory.getLogger(PublicacionServices.class);

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private CartaRepository cartaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Publicacion> findAll(){
        return publicacionRepository.findAll();
    }

    public Publicacion save(PublicacionDTo dto){

        // Registramos cuando se crea una nueva publicacion de venta
        log.info("Creando nueva publicacion para carta id: {}", dto.getCartaId());
        Publicacion publicacion = new Publicacion();

        publicacion.setPrecio(dto.getPrecio());
        publicacion.setStock(dto.getStock());
        publicacion.setTipoVendedor(dto.getTipoVendedor());

        //buscamos la carta por el id
        Carta carta = cartaRepository.findById(dto.getCartaId())
                .orElseThrow(() -> {
                    log.error("No existe la carta con el id: {}", dto.getCartaId());
                    return new NotFoundException("Carta no encontrada");
                });
        publicacion.setCarta(carta);

        //buscamos al usuario por el username
        if (dto.getUsername() != null){
            Usuario usuario = usuarioRepository.findByUsername(dto.getUsername())
                    .orElseThrow(() -> {
                        log.error("No existe el usuario: {}", dto.getUsername());
                        return new NotFoundException("Usuario no encontrado");
                    });
            publicacion.setUsuario(usuario);
        }

        Publicacion guardar = publicacionRepository.save(publicacion);
        log.info("Publicacion guardado con el id: {}", guardar.getId());
        return guardar;
    }

    public void delete(Integer id){
        // Registramos cuando se elimina una publicacion del sistema
        log.info("Eliminando publicacion con id: {}", id);

        if(!publicacionRepository.existsById(id)){
            log.warn("Intento de eliminar carta inexistente con el id: {}", id);
        }
        publicacionRepository.deleteById(id);
        log.info("Publicacion eliminada con el id: {}", id);
    }
}