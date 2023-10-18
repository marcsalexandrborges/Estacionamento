package dio.bootcamp.santander.estacionamento;

import dio.bootcamp.santander.estacionamento.entity.User;
import dio.bootcamp.santander.estacionamento.services.UserService;
import dio.bootcamp.santander.estacionamento.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


public class UserServiceTest {
    private UserService userService;

    // Repositório simulado usando Mockito
    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach()
    public void setUp() {
        // Configure o comportamento simulado do repositório, se necessário.
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void testValidUserLogin() {
        // Crie um usuário válido com nome de usuário e senha válidos.
        User validUser = new User();
        validUser.setUsername("user123");
        validUser.setPassword(passwordEncoder.encode("password123"));

        // Envie um Optional contendo o usuário válido
        Optional<User> userOptional = Optional.ofNullable(validUser); // Usando Optional.ofNullable

        // Configure o comportamento simulado do repositório
        Mockito.when(userRepository.findByUsername("user123")).thenReturn(userOptional);

        // Tente autenticar o usuário.
        User authenticatedUser = userService.authenticate("user123", "password123");

        // Verifique se o usuário autenticado não é nulo.
        assertNotNull(authenticatedUser);
    }

    @Test
    public void testInvalidUserLogin() {
        // Crie um usuário de teste com nome de usuário e senha inválidos.
        User invalidUser = new User();
        invalidUser.setUsername("user123");
        invalidUser.setPassword(passwordEncoder.encode("wrongpassword"));

        // Envolve o usuário em um Optional
        Optional<User> userOptional = Optional.of(invalidUser);

        // Configure o comportamento simulado do repositório, retornando o Optional
        Mockito.when(userRepository.findByUsername("user123")).thenReturn(userOptional);

        // Tente autenticar o usuário com uma senha incorreta.
        User authenticatedUser = userService.authenticate("user123", "password123");

        // Verifique se o usuário autenticado é nulo.
        assertNull(authenticatedUser);
    }



}
