package app.lucas.meusgastos.card.service;

import app.lucas.meusgastos.card.dto.CardDTO;
import app.lucas.meusgastos.card.dto.CardResponseDTO;
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

    public List<CardResponseDTO> findAll() {
        List<Card> cardList = cardRepository.findAll();
        List<CardResponseDTO> cardResponseAllDTOList = new ArrayList<>();

        for (Card card : cardList) {
            CardResponseDTO cardNameIdDTO = new CardResponseDTO(
                    card.getId(),
                    card.getName(),
                    card.getColor());
            cardResponseAllDTOList.add(cardNameIdDTO);
        }
        return cardResponseAllDTOList;
    }

    public void update(CardResponseDTO cardResponseDTO) {
        Card savedCard = findBYIdOrThrowError(cardResponseDTO.id());
        savedCard.setName(cardResponseDTO.name());
        savedCard.setColor(cardResponseDTO.color());
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
