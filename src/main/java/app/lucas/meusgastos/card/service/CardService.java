package app.lucas.meusgastos.card.service;

import app.lucas.meusgastos.card.dto.CardDTO;
import app.lucas.meusgastos.card.dto.CardIdNameColorDTO;
import app.lucas.meusgastos.card.entity.Card;
import app.lucas.meusgastos.card.repository.CardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public CardIdNameColorDTO save(CardDTO cardDTO) {
        Card card = Card.CardBuilder
                .builder()
                .name(cardDTO.name())
                .color(cardDTO.color())
                .username(cardDTO.username())
                .build();

        cardRepository.save(card);
        return new CardIdNameColorDTO(card.getId(), cardDTO.name(), cardDTO.color());
    }

    public List<CardIdNameColorDTO> findAll() {
        List<Card> cardList = cardRepository.findAll();
        List<CardIdNameColorDTO> cardResponseAllDTOList = new ArrayList<>();

        for (Card card : cardList) {
            CardIdNameColorDTO cardIdNameColorDTO = new CardIdNameColorDTO(
                    card.getId(),
                    card.getName(),
                    card.getColor());
            cardResponseAllDTOList.add(cardIdNameColorDTO);
        }
        return cardResponseAllDTOList;
    }

    public void update(CardIdNameColorDTO cardIdNameColorDTO) {
        Card savedCard = findBYIdOrThrowError(cardIdNameColorDTO.id());
        savedCard.setName(cardIdNameColorDTO.name());
        savedCard.setColor(cardIdNameColorDTO.color());
        cardRepository.save(savedCard);
    }

    public void delete(Long id) {
        Card card = findBYIdOrThrowError(id);
        cardRepository.delete(card);
    }

    private Card findBYIdOrThrowError(Long id) {
        return cardRepository.findById(id)
                .orElseThrow();
    }
}
