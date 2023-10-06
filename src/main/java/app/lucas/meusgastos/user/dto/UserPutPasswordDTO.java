package app.lucas.meusgastos.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserPutPasswordDTO(@NotBlank String password, @NotBlank String username) { }
