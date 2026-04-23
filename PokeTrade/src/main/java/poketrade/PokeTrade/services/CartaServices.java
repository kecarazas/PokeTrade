package poketrade.PokeTrade.services;

import poketrade.PokeTrade.model.Carta;
import poketrade.PokeTrade.repository.CartaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Carta save(Carta carta){
        return cartaRepository.save(carta);
    }

    public void delete(Integer id){
        cartaRepository.deleteById(id);
    }
}
