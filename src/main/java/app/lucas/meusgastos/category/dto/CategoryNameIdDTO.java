package app.lucas.meusgastos.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryNameIdDTO(@NotNull Long id, @NotBlank String name) {
}
