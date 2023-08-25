package app.lucas.meusgastos.card.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CardResponseDTO(@NotNull Long id, @NotBlank String name, @NotBlank String color) {
}
