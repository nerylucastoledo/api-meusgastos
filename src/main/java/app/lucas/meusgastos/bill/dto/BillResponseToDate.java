package app.lucas.meusgastos.bill.dto;

import app.lucas.meusgastos.card.entity.Card;
import app.lucas.meusgastos.category.entity.Category;
import app.lucas.meusgastos.people.entity.People;

import java.util.List;

public record BillResponseToDate(
        Double salary,
        String name,
        List<BillResponseDTO> content,
        List<Card> cardList,
        List<People> peopleList,
        List<Category> categoryList
) { }
