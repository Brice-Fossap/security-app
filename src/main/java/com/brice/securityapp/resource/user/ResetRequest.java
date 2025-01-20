package com.brice.securityapp.resource.user;

import jakarta.validation.constraints.NotNull;

public record ResetRequest(
        @NotNull
        String password
) {
}
