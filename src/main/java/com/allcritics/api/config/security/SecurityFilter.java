package com.allcritics.api.config.security;

import com.allcritics.api.service.TokenService;
import com.allcritics.api.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserService userService;

    @Lazy
    @Autowired
    public SecurityFilter(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Tenta recuperar o token do cabeçalho da requisição
        var tokenJWT = recoverToken(request);

        // 2. Se um token foi encontrado, valida e define a autenticação
        if (tokenJWT != null) {
            // Supondo que o TokenService retorna o "subject" (ex: email do usuário) do token
            var subject = tokenService.validateToken(tokenJWT);

            if (!subject.isEmpty()) {
                // Carrega os dados do usuário a partir do "subject" extraído do token
                UserDetails user = userService.loadUserByUsername(subject);
                // Cria o objeto de autenticação para o Spring Security
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                // Define a autenticação no contexto de segurança do Spring.
                // A partir daqui, o Spring considera o usuário como autenticado para esta requisição.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 3. Continua a execução da cadeia de filtros.
        // Se o token for nulo, a requisição segue sem autenticação,
        // e as regras de autorização em SecurityConfiguration decidirão se ela pode prosseguir.
        filterChain.doFilter(request, response);
    }

    /**
     * Extrai o token JWT do cabeçalho "Authorization".
     * O formato esperado é "Bearer <token>".
     */
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Remove o prefixo "Bearer " para obter apenas o token
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
