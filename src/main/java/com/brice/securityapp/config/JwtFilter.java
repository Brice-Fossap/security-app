package com.brice.securityapp.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    // Liste des endpoints à exclure
    private static final List<String> EXCLUDED_PATHS = List.of("/login", "/reset-password", "/forgot-password");

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        // Vérifier si le chemin fait partie des exclus
        if (EXCLUDED_PATHS.contains(path)) {
            filterChain.doFilter(request, response); // Passer au filtre suivant sans traitement
            return;
        }

        final String tokenHeader = request.getHeader("Authorization");

        if (isBearerToken(tokenHeader)) {
            String jwtToken = extractToken(tokenHeader);
            try {
                String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                authenticateUser(username, jwtToken, request);
            } catch (ExpiredJwtException e) {
                logger.warn("JWT token expired");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token expired");
                return;
            } catch (IllegalArgumentException e) {
                logger.error("Invalid JWT token");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token");
                return;
            }
        } else if (tokenHeader != null) {
            logger.warn("Invalid Authorization header format: must start with 'Bearer'");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization header format");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isBearerToken(String header) {
        return header != null && header.startsWith("Bearer ");
    }

    private String extractToken(String header) {
        return header.substring(7);
    }

    private void authenticateUser(String username, String jwtToken, HttpServletRequest request) {
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new IllegalArgumentException("JWT token validation failed");
            }
        }
    }
}

