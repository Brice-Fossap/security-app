package com.brice.securityapp.controller;

import com.brice.securityapp.resource.user.ForgotRequest;
import com.brice.securityapp.resource.user.ResetRequest;
import com.brice.securityapp.service.user.ResetPassword;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PasswordResetController {

    private final ResetPassword resetPassword;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> requestPasswordReset(@Valid @RequestBody ForgotRequest request) {
        resetPassword.sendResetLink(request.email());
        return ResponseEntity.ok(Map.of("message", "If the email exists, a reset link has been sent."));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @Valid @RequestBody ResetRequest request) {
        resetPassword.resetPassword(token, request.password());
        return ResponseEntity.ok(Map.of("message", "Password successfully reset."));
    }
}
