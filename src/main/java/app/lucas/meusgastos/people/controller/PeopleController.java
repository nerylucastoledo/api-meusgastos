package app.lucas.meusgastos.people.controller;

import app.lucas.meusgastos.exceptions.BadRequestException;
import app.lucas.meusgastos.generic.dto.NameIdDTO;
import app.lucas.meusgastos.people.dto.PeopleRequestDTO;
import app.lucas.meusgastos.people.dto.PeopleResponseApiDTO;
import app.lucas.meusgastos.people.service.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping
    public PeopleResponseApiDTO save(@RequestBody @Valid PeopleRequestDTO peopleDTO) {
        NameIdDTO nameIdDTO = peopleService.save(peopleDTO);
        return new PeopleResponseApiDTO(HttpStatus.CREATED.value(), new ArrayList<>(Collections.singleton(nameIdDTO)));
    }

    @GetMapping
    public PeopleResponseApiDTO findAll() {
        List<NameIdDTO> peopleList = peopleService.findAll();
        return new PeopleResponseApiDTO(HttpStatus.OK.value(), peopleList);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody @Valid NameIdDTO peopleNameIdDTO, @PathVariable Long id) {
        if (id.equals(peopleNameIdDTO.id())) {
            peopleService.update(peopleNameIdDTO);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            throw new BadRequestException("ID da url diferente do contéudo");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        peopleService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
