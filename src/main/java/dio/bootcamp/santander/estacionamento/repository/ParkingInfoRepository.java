package dio.bootcamp.santander.estacionamento.repository;

import dio.bootcamp.santander.estacionamento.entity.ParkingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingInfoRepository extends JpaRepository<ParkingInfo, Long> {
    // Métodos personalizados podem ser adicionados aqui


}

