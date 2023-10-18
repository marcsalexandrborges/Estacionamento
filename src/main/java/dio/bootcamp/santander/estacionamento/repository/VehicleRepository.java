package dio.bootcamp.santander.estacionamento.repository;

import dio.bootcamp.santander.estacionamento.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
