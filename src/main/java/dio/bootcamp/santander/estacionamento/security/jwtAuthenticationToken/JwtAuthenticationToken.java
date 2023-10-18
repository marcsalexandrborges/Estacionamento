package dio.bootcamp.santander.estacionamento.security.jwtAuthenticationToken;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String token;

    public JwtAuthenticationToken(UserDetails userDetails, String token) {
        super(userDetails, null, userDetails.getAuthorities());
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
