package app.lucas.meusgastos.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserSalaryDTO(@NotNull Long id, @NotNull Double salary) {
}
