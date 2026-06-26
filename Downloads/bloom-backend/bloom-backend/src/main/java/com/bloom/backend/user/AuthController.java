package com.bloom.backend.user;

import com.bloom.backend.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User saved = userService.registerUser(user);
        String token = jwtUtil.generateToken(saved.getEmail());
        return ResponseEntity.ok(Map.of("token", token, "email", saved.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        return userService.findByEmail(email)
                .filter(u -> new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().matches(password, u.getPassword()))
                .map(u -> ResponseEntity.ok(Map.of("token", jwtUtil.generateToken(u.getEmail()), "email", u.getEmail())))
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Invalid credentials")));
    }
}
