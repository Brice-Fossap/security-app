package com.brice.securityapp.service.user;

import com.brice.securityapp.config.JwtResetPasswordToken;
import com.brice.securityapp.exception.ResourceNotFoundException;
import com.brice.securityapp.repository.UserRepository;
import com.brice.securityapp.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetPassword {

    private final UserRepository userRepository;
    private final JwtResetPasswordToken jwtResetPasswordToken;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public void sendResetLink(String email) {
        userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("User not found "));

        String token = jwtResetPasswordToken.generateResetToken(email);
        String resetLink = "http://localhost:4200/reset-password?token=" + token;

        emailService.execute(
                email,
                "Password Reset Request",
                "Click the link to reset your password: " + resetLink
        );
    }

    public void resetPassword(String token, String newPassword) {
        String email = jwtResetPasswordToken.validateResetToken(token);
        var user = userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("User not found "));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
