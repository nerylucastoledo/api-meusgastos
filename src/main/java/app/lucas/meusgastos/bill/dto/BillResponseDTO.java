package app.lucas.meusgastos.bill.dto;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public record BillResponseDTO(
        Long id,
        String item,
        Double value,
        String card,
        String people,
        String category,
        String date,
        String description
) { }
