package dio.bootcamp.santander.estacionamento.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;
    private static final String SECRET_KEY = "ApJuquey@121b";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        // Lógica para extrair o token JWT da solicitação, por exemplo, dos cabeçalhos.
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            // Remove o prefixo "Bearer " para obter apenas o token JWT
            return token.substring(7);
        }

        return null; // Nenhum token encontrado na solicitação

    }

    public boolean validateToken(String token) {
        // Lógica para validar se o token JWT é válido.
        try {
            // Verifique se o token JWT é válido (assinatura, tempo de expiração, etc.)
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Captura exceções se o token não for válido
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        // Lógica para extrair o nome de usuário do token JWT.
        // Aqui, você deve verificar a assinatura do token e extrair as reivindicações.
        // Certifique-se de tratar exceções apropriadas.

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY) // Use sua chave secreta aqui
                    .parseClaimsJws(token)
                    .getBody();

            // Extrair o nome de usuário da reivindicação do token (por exemplo, "sub" é comumente usado para isso).
            return claims.getSubject();
        } catch (Exception e) {
            // Lida com exceções apropriadas, por exemplo, TokenExpiredException.
            return null; // Se houver um erro ou o token for inválido, retorne null.
        }
    }

}

