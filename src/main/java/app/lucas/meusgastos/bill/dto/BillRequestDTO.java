package app.lucas.meusgastos.bill.dto;

import jakarta.validation.constraints.NotBlank;

public record BillRequestDTO(@NotBlank String username, @NotBlank String date) {
}
