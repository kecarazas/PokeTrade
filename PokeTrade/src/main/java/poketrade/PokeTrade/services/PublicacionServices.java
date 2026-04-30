package poketrade.PokeTrade.services;

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
        Publicacion publicacion = new Publicacion();

        publicacion.setPrecio(dto.getPrecio());
        publicacion.setStock(dto.getStock());
        publicacion.setTipoVendedor(dto.getTipoVendedor());

        //buscamos la carta por el id
        Carta carta = cartaRepository.findById(dto.getCartaId())
                .orElseThrow(() -> new NotFoundException("Carta no encontrada"));
        publicacion.setCarta(carta);

        //buscamos al usuario por el username
        if (dto.getUsername() != null){
            Usuario usuario = usuarioRepository.findByUsername(dto.getUsername())
                    .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            publicacion.setUsuario(usuario);
        }
        return publicacionRepository.save(publicacion);
    }

    public void delete(Integer id){
        publicacionRepository.deleteById(id);
    }



}
