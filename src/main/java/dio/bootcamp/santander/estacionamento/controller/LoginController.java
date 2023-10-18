
package dio.bootcamp.santander.estacionamento.controller;

import dio.bootcamp.santander.estacionamento.dtos.Login;
import dio.bootcamp.santander.estacionamento.dtos.Sessao;
import dio.bootcamp.santander.estacionamento.entity.User;
import dio.bootcamp.santander.estacionamento.repository.UserRepository;
import dio.bootcamp.santander.estacionamento.security.SecurityConfig;
import dio.bootcamp.santander.estacionamento.security.jwtAuthenticationToken.JWTCreator;
import dio.bootcamp.santander.estacionamento.security.jwtAuthenticationToken.JWTObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login){
        User user = repository.findByUsername(login.getUsername()).orElse(null);
        if(user!=null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            String chave = securityConfig.getKey(); // Aqui você acessa a chave JWT
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, chave, jwtObject));
            return sessao;
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }

}

