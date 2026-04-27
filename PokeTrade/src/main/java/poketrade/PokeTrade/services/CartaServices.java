package poketrade.PokeTrade.services;

import poketrade.PokeTrade.model.Carta;
import poketrade.PokeTrade.DTo.CartaDTo;
import poketrade.PokeTrade.repository.CartaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartaServices {

    @Autowired
    private CartaRepository cartaRepository;

    public List<Carta> findAll(){
        return cartaRepository.findAll();
    }

    public Carta findById(Integer id){
        return cartaRepository.findById(id).get();
    }

    public Carta save(CartaDTo dto){
        Carta carta = new Carta();

        carta.setNombre(dto.getNombre());
        carta.setPs(dto.getPs());
        carta.setTipo(dto.getTipo());
        carta.setHabilidad(dto.getHabilidad());
        carta.setDescripcionHabilidad(dto.getDescripcionHabilidad());
        carta.setCosteEnergia(dto.getCosteEnergia());
        carta.setRetirada(dto.getRetirada());
        carta.setAtaque(dto.getAtaque());
        carta.setDanoAtaque(dto.getDanoAtaque());
        carta.setDescripcionAtaque(dto.getDescripcionAtaque());
        carta.setDebilidad(dto.getDebilidad());
        carta.setResistencia(dto.getResistencia());
        carta.setRareza(dto.getRareza());

        return  cartaRepository.save(carta);
    }

    public List<Carta> saveLista(List<CartaDTo> dtos){
        List<Carta> cartas = new ArrayList<>();
        for (CartaDTo dto : dtos) {
            Carta carta = new Carta();

            carta.setNombre(dto.getNombre());
            carta.setPs(dto.getPs());
            carta.setTipo(dto.getTipo());
            carta.setHabilidad(dto.getHabilidad());
            carta.setDescripcionHabilidad(dto.getDescripcionHabilidad());
            carta.setCosteEnergia(dto.getCosteEnergia());
            carta.setRetirada(dto.getRetirada());
            carta.setAtaque(dto.getAtaque());
            carta.setDanoAtaque(dto.getDanoAtaque());
            carta.setDescripcionAtaque(dto.getDescripcionAtaque());
            carta.setDebilidad(dto.getDebilidad());
            carta.setResistencia(dto.getResistencia());
            carta.setRareza(dto.getRareza());

            cartas.add(carta);
        }
        return cartaRepository.saveAll(cartas);
    }

    public void delete(Integer id){
        cartaRepository.deleteById(id);
    }
}
