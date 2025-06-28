package kotami.studybuddy.controller;

import jakarta.validation.Valid;
import kotami.studybuddy.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider      jwtTokenProvider;

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {

        // 1. Verify credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password()));

        // 2. Generate JWT
        String token = jwtTokenProvider.generateToken(authentication);

        // 3. Return it
        return new TokenResponse(token);
    }

    /* ──────────────────────────── DTOs ──────────────────────────── */
    public record LoginRequest(String username, String password) {}

    public record TokenResponse(String token) {}
}