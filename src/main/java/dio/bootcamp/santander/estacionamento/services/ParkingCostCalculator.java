package dio.bootcamp.santander.estacionamento.services;

public class ParkingCostCalculator {

    private ParkingCostStrategy costStrategy;

    public ParkingCostCalculator(ParkingCostStrategy costStrategy) {
        this.costStrategy = costStrategy;
    }

    public double calculateParkingCost(double duration) {
        // Aqui você implementaria a lógica de cálculo dos custos
        // usando a estratégia costStrategy e a duração do estacionamento.
        return costStrategy.calculateParkingCost(duration);
    }

}
