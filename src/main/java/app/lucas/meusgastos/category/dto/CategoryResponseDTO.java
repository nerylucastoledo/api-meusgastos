package app.lucas.meusgastos.category.dto;

import app.lucas.meusgastos.generic.dto.NameIdResponseDTO;

import java.util.List;

public record CategoryResponseDTO(Integer status, List<NameIdResponseDTO> content) { }
