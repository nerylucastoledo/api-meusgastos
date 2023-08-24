package app.lucas.meusgastos.card.service;

import app.lucas.meusgastos.card.dto.CardDTO;
import app.lucas.meusgastos.card.entity.Card;
import app.lucas.meusgastos.card.repository.CardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public CardDTO save(CardDTO cardDTO) {
        Card card = Card.CardBuilder
                .builder()
                .name(cardDTO.name())
                .color(cardDTO.color())
                .username(cardDTO.username())
                .build();

        cardRepository.save(card);
        return cardDTO;
    }
}
