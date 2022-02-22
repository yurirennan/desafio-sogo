package com.yuri.desafio.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIX_HEADER = "Bearer ";

    //    @Value("${jwt.secret}")
    private String jwtSecret = "01075df752c01ba95dee395efa7b6304";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(AUTH_HEADER);

        if (token == null || !token.startsWith(PREFIX_HEADER)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            token = token.replace(PREFIX_HEADER, "");

            Algorithm algotirithm = Algorithm.HMAC256(jwtSecret.getBytes());
            JWTVerifier verifier = JWT.require(algotirithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String userId = decodedJWT.getSubject();

            if (userId.isEmpty()) {
                System.out.println("authorization Filter");
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userId, null, new ArrayList<>()
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);

        } catch (Exception ex) {
            Map<String, String> tokens = new HashMap<>();
            tokens.put("error", "Token Invalido ou Expirado!");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(401);
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        }
    }
}
