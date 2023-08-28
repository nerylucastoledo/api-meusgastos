package app.lucas.meusgastos.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(@NotBlank String email, @NotBlank String password) {
}
