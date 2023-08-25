package app.lucas.meusgastos.card.controller;

import app.lucas.meusgastos.card.dto.CardDTO;
import app.lucas.meusgastos.card.dto.CardIdNameColorDTO;
import app.lucas.meusgastos.card.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<CardIdNameColorDTO> save(@RequestBody @Valid CardDTO cardDTO) {
        return new ResponseEntity(cardService.save(cardDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CardIdNameColorDTO> findAll() {
        return cardService.findAll();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody @Valid CardIdNameColorDTO cardIdNameColorDTO) {
        cardService.update(cardIdNameColorDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        cardService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
