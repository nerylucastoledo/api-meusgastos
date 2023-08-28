package app.lucas.meusgastos.card.dto;

import java.util.List;

public record CardResponseApiDTO(Integer status, List<CardIdNameColorDTO> content) {
}
