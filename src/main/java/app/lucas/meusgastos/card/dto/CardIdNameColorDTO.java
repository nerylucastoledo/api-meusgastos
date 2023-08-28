package app.lucas.meusgastos.card.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

public record CardIdNameColorDTO(@NotNull Long id, @NotBlank String name, @NotBlank String color) {
}
