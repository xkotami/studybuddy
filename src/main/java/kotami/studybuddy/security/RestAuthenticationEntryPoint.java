package kotami.studybuddy.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.Map;

/**
 * Sends a simple JSON object when user is not authenticated.
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest  request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> body = Map.of(
                "status", HttpServletResponse.SC_UNAUTHORIZED,
                "error",  "Unauthorized",
                "message", authException.getMessage(),
                "path",   request.getRequestURI()
        );

        MAPPER.writeValue(response.getOutputStream(), body);
    }
}