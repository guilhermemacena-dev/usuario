package com.guilherme.usuario.infrastructure.security;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.guilherme.usuario.infrastructure.exceptions.dto.ErrorResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.time.LocalDateTime;

public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtRequestFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

         final String authorizationHeader = request.getHeader("Authorization");

        try {
           
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                
                final String token = authorizationHeader.substring(7);
                
                final String username = jwtUtil.extrairEmailToken(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    
                    if (jwtUtil.validateToken(token, username)) {
                        
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails, null, userDetails.getAuthorities());
                        
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }

            chain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(buildError(HttpStatus.UNAUTHORIZED.value(),
                    "Token expirado",
                    request.getRequestURI(),
                    e.getMessage()));
        }
    }

    private String buildError(int status, String mensage, String path, String error) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .menssage(mensage)
                .error(error)
                .status(status)
                .path(path)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            return objectMapper.writeValueAsString(errorResponseDTO);
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }
    }
}
