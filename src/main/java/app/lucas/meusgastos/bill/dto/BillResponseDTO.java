package app.lucas.meusgastos.bill.dto;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public record BillResponseDTO(
        String item,
        Double value,
        String description,
        String card,
        String people,
        String category,
        String date
) { }
