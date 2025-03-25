package vn.edu.stu.backend_service.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.edu.stu.backend_service.service.UserServiceDetail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class CustomizeRequestFilter extends OncePerRequestFilter {

    private final UserServiceDetail userServiceDetail;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            sendUnauthorizedResponse(response, request.getRequestURI(), "Please provide email and password in the header");
            return;
        }

        try {
            String[] credentials = extractCredentials(authHeader);
            String email = credentials[0];
            String password = credentials[1];

            UserDetails user = userServiceDetail.loadUserByUsername(email);
            if (!passwordEncoder.matches(password, user.getPassword())) {
                sendUnauthorizedResponse(response, request.getRequestURI(), "Incorrect username or password in header");
                return;
            }

        } catch (Exception e) {
            sendUnauthorizedResponse(response, request.getRequestURI(), "Incorrect username or password in header");
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api/v1/auth/");
    }


    private String[] extractCredentials(String authHeader) {
        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
        String[] values = credentials.split(":", 2);

        if (values.length != 2) {
            throw new IllegalArgumentException("Authorization header invalid");
        }

        return values;
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String path, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("""
            {
                "status": 401,
                "error": "Unauthorized",
                "message": "%s",
                "path": "%s"
            }
        """.formatted(message, path));
    }
}
