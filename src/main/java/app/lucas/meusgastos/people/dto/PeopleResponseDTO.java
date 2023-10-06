package app.lucas.meusgastos.people.dto;

import app.lucas.meusgastos.generic.dto.NameIdResponseDTO;

import java.util.List;

public record PeopleResponseDTO(Integer status, List<NameIdResponseDTO> content) {
}
