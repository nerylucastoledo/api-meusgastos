package app.lucas.meusgastos.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        @NotBlank(message = "Nome do cartão não pode ser vazio!")
        String name,
        @NotBlank(message = "Username deve ser válido!")
        String username
) { }
