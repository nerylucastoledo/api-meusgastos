package app.lucas.meusgastos.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        String name,
        @NotBlank(message = "Sobrenome não pode ser vazio!")
        String lastname,
        @NotNull(message = "Salário não pode ser vazio!")
        Double salary,
        @NotBlank(message = "E-mail não pode ser vazio!")
        String email,
        @NotBlank(message = "Senha não pode ser vazia!")
        String password
) {
}
