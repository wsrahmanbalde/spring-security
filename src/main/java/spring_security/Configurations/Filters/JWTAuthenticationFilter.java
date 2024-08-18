package spring_security.Configurations.Filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import spring_security.Models.AppUser;

import java.io.IOException;
import java.util.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AppUser appUser = null;

        try {
            appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(Objects.requireNonNull(appUser).getUsername(),appUser.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();

        List<String> roles = new ArrayList<>();

        user.getAuthorities().forEach(au-> {
            roles.add(au.getAuthority());
        });

        String jwt = JWT.create(). withSubject(user.getUsername()).
                withSubject(user.getUsername()).
                withArrayClaim("roles", roles.toArray(new String[roles.size()])).
                withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000)).
                withIssuer(request.getRequestURL().toString()).
                sign(Algorithm.HMAC256("balde2024"));



        String jwtRefreshToken = JWT.create(). withSubject(user.getUsername()).
                withSubject(user.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis()+15*60*1000)).
                withIssuer(request.getRequestURL().toString()).
                sign(Algorithm.HMAC256("balde2024"));

        Map<String,String> idToken = new HashMap<>();
        idToken.put("access-token",jwt);
        idToken.put("refresh-token",jwtRefreshToken);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(),idToken);
    }
}
