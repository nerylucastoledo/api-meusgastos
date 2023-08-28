package app.lucas.meusgastos.bill.dto;

import java.util.List;

public record BillResponseToDate(Double salary, String name, List<BillResponseDTO> content) {
}
