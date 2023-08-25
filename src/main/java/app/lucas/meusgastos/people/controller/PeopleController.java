package app.lucas.meusgastos.people.controller;

import app.lucas.meusgastos.people.dto.PeopleDTO;
import app.lucas.meusgastos.people.dto.PeopleNameIdDTO;
import app.lucas.meusgastos.people.service.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid PeopleDTO peopleDTO) {
        return new ResponseEntity(peopleService.save(peopleDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PeopleNameIdDTO> findAll() {
        return peopleService.findAll();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody PeopleNameIdDTO peopleNameIdDTO) {
        peopleService.update(peopleNameIdDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        peopleService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}