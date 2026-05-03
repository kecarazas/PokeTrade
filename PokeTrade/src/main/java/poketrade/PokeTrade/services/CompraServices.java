package poketrade.PokeTrade.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poketrade.PokeTrade.DTo.CompraDTo;
import poketrade.PokeTrade.exception.NotFoundException;
import poketrade.PokeTrade.model.*;
import poketrade.PokeTrade.repository.*;

import java.util.List;

@Transactional
@Service
public class CompraServices {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    public List<Compra> findAllCompra(){
        return compraRepository.findAll();
    }

    public Compra comprar(CompraDTo dto){
        Compra compra = new Compra();

        //buscamos la publicacion por el id
        Publicacion publicacion = publicacionRepository.findById(dto.getPublicacionId())
                .orElseThrow(()-> new NotFoundException("Publicacion no encontrada"));

        //buscamos al usuario por el username
        if (dto.getUsername() != null){
            Usuario usuario = usuarioRepository.findByUsername(dto.getUsername())
                    .orElseThrow(()-> new NotFoundException("Usuario no encontrada"));
            compra.setUsuario(usuario);
        }

        if(publicacion.getStock() <= 0){
            throw new NotFoundException("No hay stock disponible");
        }

        if(publicacion.getStock() < dto.getCantidad()){
            throw new NotFoundException("No hay cantidad disponible");
        }

        //actualizamos el stock de cada publicacion cuando se haga una compra
        publicacion.setStock(publicacion.getStock() - dto.getCantidad());

        compra.setPublicacion(publicacion);
        compra.setCantidad(dto.getCantidad());
        compra.setTotal(compra.getCantidad() * publicacion.getPrecio());

        publicacionRepository.save(publicacion);

        return compraRepository.save(compra);
    }


}
