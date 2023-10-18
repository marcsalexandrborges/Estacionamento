package dio.bootcamp.santander.estacionamento.repository;

import dio.bootcamp.santander.estacionamento.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);


    // Outros métodos podem ser adicionados aqui, se necessário



}
