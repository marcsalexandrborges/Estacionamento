package dio.bootcamp.santander.estacionamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("internationalParkingCostStrategy")
public class InternationalParkingCostStrategy implements ParkingCostStrategy {
    private String country;
    @Value("${international.parking.exchangeRate}")
    private double exchangeRate;

    @Autowired
    public InternationalParkingCostStrategy(@Value("${international.parking.country}") String country, @Value("${international.parking.exchangeRate}") double exchangeRate) {
        this.country = country;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public double calculateParkingCost(double duration) {
        // Implemente a lógica para calcular o custo de estacionamento internacional com base na duração.
        double cost = 5.0 * duration; // Custo base por hora em moeda local
        if ("USA".equalsIgnoreCase(country)) {
            cost *= exchangeRate; // Aplicar a taxa de câmbio se o país for os EUA
        }
        return cost;
    }

}
