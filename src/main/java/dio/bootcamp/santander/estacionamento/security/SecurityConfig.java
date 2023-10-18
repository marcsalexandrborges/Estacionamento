package dio.bootcamp.santander.estacionamento.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Defina a constante EXPIRATION aqui
    public static final int EXPIRATION = 3600; // Defina o valor apropriado
    public static final String PREFIX = "Bearer"; // Aqui você define o PREFIX
    private static final String KEY = "ApJuquey@121b";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/public/users/create").permitAll()
                .antMatchers("/api/public/users").permitAll()
                .antMatchers("/api/public/users/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/**.html**", "/v3/api-docs**", "/v2/api-docs**","/webjars/**", "/configuration/**", "/swagger-resources/**","/swagger-ui/index.html/**","/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();
    }


    public String getKey() {
        return KEY;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}