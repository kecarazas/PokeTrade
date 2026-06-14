package poketrade.PokeTrade.services;

import com.sun.jdi.event.ExceptionEvent;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poketrade.PokeTrade.DTo.CompraDTo;
import poketrade.PokeTrade.DTo.UsuarioDTO;
import poketrade.PokeTrade.cliente.UsuarioClient;
import poketrade.PokeTrade.exception.NotFoundException;
import poketrade.PokeTrade.exception.RemoteServiceException;
import poketrade.PokeTrade.model.*;
import poketrade.PokeTrade.repository.*;

import java.util.List;

@Transactional
@Service
public class CompraServices {

    private static final Logger log = LoggerFactory.getLogger(CompraServices.class);

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private PublicacionRepository publicacionRepository;

    public List<Compra> findAllCompra(){
        return compraRepository.findAll();
    }

    public Compra comprar(CompraDTo dto){
        log.info("Iniciando compra con el usuario: {}", dto.getUsername());

        Compra compra = new Compra();

        //buscamos la publicacion por el id
        Publicacion publicacion = publicacionRepository.findById(dto.getPublicacionId())
                .orElseThrow(() -> {
                    log.error("No existe el publicacion con el id: {}", dto.getPublicacionId());
                    return new NotFoundException("Publicacion no encontrada");
                });

        //condicion que nos ayuda a buscar al usuario por el username
        if (dto.getUsername() != null){
            try{
                UsuarioDTO usuario = usuarioClient.findByUsername(dto.getUsername());
                compra.setUsuarioId(usuario.getId());
            }catch (FeignException.NotFound e){
                log.error("Usuario no encontrado", e);
                throw new NotFoundException("Usuario no encontrado");
            }catch (Exception e){
                log.error("Error al comunicarse con el servicio usuario", e);
                throw new RemoteServiceException("No fue posible comunicarse con el servicio usuario");
            }
        }

        //condicion que nos lanza una advertencia cuando la publicacion tenga 0 stock
        if(publicacion.getStock() == 0){
            log.warn("Compra rechazada: no hay stock en la publicacion {}", publicacion.getId());
            throw new NotFoundException("No hay stock disponible");
        }

        //condicion que nos advierte que no hay suficiente stock cuando el stock es menor a la cantidad solicitada al momento de comprar
        if(publicacion.getStock() < dto.getCantidad()){
            log.warn("Compra rechazada: no hay suficiente stock en la publicacion {}", publicacion.getId());
            throw new NotFoundException("No hay cantidad disponible");
        }

        //actualizamos el stock de cada publicacion cuando se haga una compra
        publicacion.setStock(publicacion.getStock() - dto.getCantidad());

        compra.setPublicacion(publicacion);
        compra.setCantidad(dto.getCantidad());
        compra.setTotal(compra.getCantidad() * publicacion.getPrecio());

        publicacionRepository.save(publicacion);

        Compra guardar = compraRepository.save(compra);
        log.info("compra realizada correctamente");
        return  guardar;
    }


}
