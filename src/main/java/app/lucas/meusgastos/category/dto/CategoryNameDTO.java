package app.lucas.meusgastos.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryNameDTO(@NotBlank String name) {
}
