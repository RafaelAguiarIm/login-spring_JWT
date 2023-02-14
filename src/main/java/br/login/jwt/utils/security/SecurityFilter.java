package br.login.jwt.utils.security;

import br.login.jwt.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //Identifica que é uma classe genérica
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    //Garante que essa classe será executada apenas uma unica vez a cada requisição
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        //chamada do método que valida o token JWT
       var subject = tokenService.getSubject(tokenJWT);

        filterChain.doFilter(request,response);

    }
    //Método para recuperar o token enviado ao cabeçalho Authorization
    private String recuperarToken(HttpServletRequest request){
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null){
            throw new RuntimeException("Token JWT não foi enviado ao cabeçalho Authorization!");
        }

        return authorizationHeader.replace("Bearer ", "");
    }
}
