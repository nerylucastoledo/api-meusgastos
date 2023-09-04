package app.lucas.meusgastos.card.service;

import app.lucas.meusgastos.card.dto.CardDTO;
import app.lucas.meusgastos.card.dto.CardIdNameColorDTO;
import app.lucas.meusgastos.card.dto.CardPutDTO;
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

    public List<CardIdNameColorDTO> findAll(String username) {
        List<Card> cardList = cardRepository.findAllByUsername(username);
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

    public void update(CardPutDTO cardPutDTO) {
        Card savedCard = findBYIdOrThrowError(cardPutDTO.id());

        if (savedCard.getUsername().equals(cardPutDTO.username())) {
            savedCard.setName(cardPutDTO.name());
            savedCard.setColor(cardPutDTO.color());
            cardRepository.save(savedCard);
        }
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
