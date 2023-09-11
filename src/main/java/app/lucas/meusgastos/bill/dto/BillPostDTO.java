package app.lucas.meusgastos.bill.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BillPostDTO(
        @NotBlank String item,
        @NotBlank String username,
        @NotNull Double value,
        @NotBlank String people,
        @NotBlank String category,
        @NotBlank String date,
        @NotBlank String card) { }
