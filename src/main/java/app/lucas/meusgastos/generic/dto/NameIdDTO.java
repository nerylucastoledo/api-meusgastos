package app.lucas.meusgastos.generic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NameIdDTO(@NotNull Long id, @NotBlank String name) { }
