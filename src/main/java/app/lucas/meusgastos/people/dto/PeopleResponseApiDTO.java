package app.lucas.meusgastos.people.dto;

import app.lucas.meusgastos.generic.dto.NameIdDTO;

import java.util.List;

public record PeopleResponseApiDTO(Integer status, List<NameIdDTO> content) {
}
