package dio.bootcamp.santander.estacionamento.controller;

import dio.bootcamp.santander.estacionamento.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/calculateCost")
    public double calculateParkingCost(@RequestParam String location, @RequestParam double duration) {
        return parkingService.calculateParkingCost(location, duration);
    }
}
