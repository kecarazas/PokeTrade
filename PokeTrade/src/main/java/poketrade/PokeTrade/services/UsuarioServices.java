package poketrade.PokeTrade.services;

import poketrade.PokeTrade.DTo.UsuarioDTo;
import poketrade.PokeTrade.model.Usuario;
import poketrade.PokeTrade.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
@Service
public class UsuarioServices {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario>  findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario save(UsuarioDTo dto){
        Usuario usuario = new Usuario();

        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());

        return usuarioRepository.save(usuario);
    }

    public void delete(Integer id){
        usuarioRepository.deleteById(id);
    }

}
