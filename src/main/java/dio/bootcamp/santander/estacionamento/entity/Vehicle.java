package dio.bootcamp.santander.estacionamento.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Setter
@Getter
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plateNumber;
    private String brand;

    // Adicione a anotação @ManyToOne para definir o relacionamento
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Cada veículo está associado a um usuário

    public Vehicle() {
    }

    public Vehicle(String plateNumber, String brand, User user) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.user = user;
    }


}
