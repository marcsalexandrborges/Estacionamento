package dio.bootcamp.santander.estacionamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {
    private final ParkingCostStrategy localCostStrategy;
    private final ParkingCostStrategy internationalCostStrategy;

    @Autowired
    public ParkingService(@Qualifier("localParkingCostStrategy") ParkingCostStrategy localCostStrategy,
                          @Qualifier("internationalParkingCostStrategy") ParkingCostStrategy internationalCostStrategy) {
        this.localCostStrategy = localCostStrategy;
        this.internationalCostStrategy = internationalCostStrategy;
    }

    public double calculateParkingCost(String location, double duration) {
        // Lógica para determinar a estratégia com base na localização
        ParkingCostStrategy selectedStrategy;

        if ("local".equalsIgnoreCase(location)) {
            selectedStrategy = localCostStrategy;
        } else {
            selectedStrategy = internationalCostStrategy;
        }

        // Calcula o custo com a estratégia selecionada
        double cost = selectedStrategy.calculateParkingCost(duration);

        return cost;
    }
}