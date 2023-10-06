package app.lucas.meusgastos.people.dto;

import jakarta.validation.constraints.NotBlank;

public record PeopleCreateDTO(
        @NotBlank(message = "Nome do cartão não pode ser vazio!")
        String name,
        @NotBlank(message = "Username deve ser válido!")
        String username
) { }
