package app.lucas.meusgastos.card.controller;

import app.lucas.meusgastos.card.dto.CardDTO;
import app.lucas.meusgastos.card.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid CardDTO cardDTO) {
        return new ResponseEntity(cardService.save(cardDTO), HttpStatus.CREATED);
    }
}
