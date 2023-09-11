package app.lucas.meusgastos.card.controller;

import app.lucas.meusgastos.card.dto.CardDTO;
import app.lucas.meusgastos.card.dto.CardIdNameColorDTO;
import app.lucas.meusgastos.card.dto.CardPutDTO;
import app.lucas.meusgastos.card.dto.CardResponseApiDTO;
import app.lucas.meusgastos.card.service.CardService;
import app.lucas.meusgastos.exceptions.BadRequestException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("card")
@CrossOrigin("*")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public CardResponseApiDTO save(@RequestBody @Valid CardDTO cardDTO) {
        CardIdNameColorDTO cardIdNameColorDTO = cardService.save(cardDTO);
        return new CardResponseApiDTO(HttpStatus.CREATED.value(), new ArrayList<>(Collections.singleton(cardIdNameColorDTO)));
    }

    @GetMapping
    public CardResponseApiDTO findAll(@RequestParam String username) {
        if (username.isEmpty()) {
            throw new BadRequestException("Username não pode ser vazio");
        }

        List<CardIdNameColorDTO> cardIdNameColorDTOList = cardService.findAll(username);
        return new CardResponseApiDTO(HttpStatus.OK.value(), cardIdNameColorDTOList);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody @Valid CardPutDTO cardPutDTO, @PathVariable Long id) {
        if (id.equals(cardPutDTO.id())) {
            cardService.update(cardPutDTO);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            throw new BadRequestException("ID da url diferentsde do contéudo");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        cardService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
