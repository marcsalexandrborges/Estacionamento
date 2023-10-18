package dio.bootcamp.santander.estacionamento.services;

import dio.bootcamp.santander.estacionamento.entity.User;
import dio.bootcamp.santander.estacionamento.repository.UserRepository;
import dio.bootcamp.santander.estacionamento.services.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }

        return null; // Autenticação falhou
    }

    public Optional<User> createUser(String username, String password) {

        // Verifique se o nome de usuário já existe
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("O nome de usuário já está em uso.");
        }

        // Crie um novo objeto User a partir dos dados fornecidos em username e password
        User user = new User();
        user.setUsername(username);

        // Hash da senha antes de salvar no banco de dados
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        return Optional.ofNullable(userRepository.save(user));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            // Caso o ID não exista, capture a exceção e retorne false ou lide com ela conforme sua necessidade.
            return false;
        }
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(updatedUser.getUsername());
            String encodedPassword = passwordEncoder.encode(updatedUser.getPassword());
            user.setPassword(encodedPassword);

            return userRepository.save(user);
        } else {
            // Lida com a situação em que o usuário não existe
            return null;
        }
    }
}
