package dio.bootcamp.santander.estacionamento.services;

import org.springframework.stereotype.Service;


@Service
public class LocalParkingCostStrategy implements ParkingCostStrategy {

    @Override
    public double calculateParkingCost(double duration) {
        // Implemente a lógica para calcular o custo de estacionamento local com base na duração.
        // Por exemplo, você pode considerar taxas de hora, dia, semana, etc.
        // Simplesmente retorne o custo calculado com base na duração.
        return 10.0 * duration; // Exemplo de custo de estacionamento local
    }
}
