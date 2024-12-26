package com.brice.securityapp.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String tokenHeader = request.getHeader("Authorization");

        if (isBearerToken(tokenHeader)) {
            String jwtToken = extractToken(tokenHeader);
            try {
                String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                authenticateUser(username, jwtToken, request);
            } catch (ExpiredJwtException e) {
                logger.warn("JWT token expired", e);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to extract JWT token", e);
            }
        } else if (tokenHeader != null) {
            logger.warn("Invalid Authorization header format: must start with 'Bearer'");
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
            }
        }
    }
}
