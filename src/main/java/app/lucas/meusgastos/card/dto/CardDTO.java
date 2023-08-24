package app.lucas.meusgastos.card.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CardDTO(
        @NotBlank(message = "Nome do cartão não pode ser vazio!")
        String name,
        @NotBlank(message = "Username deve ser válido!")
        String username,
        @NotNull(message = "Cor do cartão não pode ser vazia!")
        String color
) { }
