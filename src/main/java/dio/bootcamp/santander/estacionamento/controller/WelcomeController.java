package dio.bootcamp.santander.estacionamento.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Operation(summary = "Mensagem de boas-vindas")
    @GetMapping("/")
    public String welcome() {
        return "Welcome to My Spring Boot Web API";
    }

    @Operation(summary = "Endpoint de usuários")
    @GetMapping("/users")
    public String users() {
        return "Authorized user";
    }

    @Operation(summary = "Endpoint de gerentes")
    @GetMapping("/managers")
    public String managers() {
        return "Authorized manager";
    }
}

