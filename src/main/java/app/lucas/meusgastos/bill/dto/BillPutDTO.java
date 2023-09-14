package app.lucas.meusgastos.bill.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BillPutDTO(Long id, @NotBlank String item, @NotNull Double value) {
}
