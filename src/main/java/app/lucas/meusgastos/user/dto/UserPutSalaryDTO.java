package app.lucas.meusgastos.user.dto;

import jakarta.validation.constraints.NotNull;

public record UserPutSalaryDTO(@NotNull Long id, @NotNull Double salary) {
}
