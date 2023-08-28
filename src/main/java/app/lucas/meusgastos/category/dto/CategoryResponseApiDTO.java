package app.lucas.meusgastos.category.dto;

import app.lucas.meusgastos.generic.dto.NameIdDTO;

import java.util.List;

public record CategoryResponseApiDTO(Integer status, List<NameIdDTO> content) {
}
