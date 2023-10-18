package dio.bootcamp.santander.estacionamento.controller;

import dio.bootcamp.santander.estacionamento.entity.User;
import dio.bootcamp.santander.estacionamento.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/users")
    public void postUser(@RequestBody User user) {
        service.createUser(user.getUsername(), user.getPassword());
    }
}



