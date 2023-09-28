package app.lucas.meusgastos.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdatePasswordPost(@NotBlank String password, @NotBlank String username) { }
