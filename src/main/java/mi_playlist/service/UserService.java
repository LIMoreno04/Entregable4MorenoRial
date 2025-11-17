package mi_playlist.service;

import mi_playlist.model.UserModel;
import mi_playlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> obtenerTodosLosUsuarios() {
        return userRepository.findAll();
    }

    public UserModel obtenerUsuarioPorId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserModel obtenerUsuarioPorNombre(String nombre) {
        return userRepository.findByNombre(nombre);
    }

    public UserModel crearUsuario(String nombre) {
        UserModel usuario = new UserModel(nombre);
        return userRepository.save(usuario);
    }

    public UserModel guardarUsuario(UserModel usuario) {
        return userRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        userRepository.deleteById(id);
    }
}
