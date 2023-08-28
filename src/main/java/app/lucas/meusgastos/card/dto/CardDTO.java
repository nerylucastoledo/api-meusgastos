package app.lucas.meusgastos.card.dto;

import jakarta.validation.constraints.NotBlank;

public record CardDTO(
        @NotBlank(message = "Nome do cartão não pode ser vazio!")
        String name,
        @NotBlank (message = "Username deve ser válido!")
        String username,
        @NotBlank(message = "Cor do cartão não pode ser vazia!")
        String color
) { }
