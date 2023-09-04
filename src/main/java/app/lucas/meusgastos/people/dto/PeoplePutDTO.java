package app.lucas.meusgastos.people.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PeoplePutDTO(@NotNull Long id, @NotBlank String name, @NotBlank String username) {
}
