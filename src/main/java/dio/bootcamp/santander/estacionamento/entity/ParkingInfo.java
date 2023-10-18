package dio.bootcamp.santander.estacionamento.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ParkingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private double cost;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public ParkingInfo() {
    }

    public ParkingInfo(LocalDateTime checkInTime, LocalDateTime checkOutTime, double cost, Vehicle vehicle) {
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.cost = cost;
        this.vehicle = vehicle;
    }
}