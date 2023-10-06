package app.lucas.meusgastos.people.controller;

import app.lucas.meusgastos.exceptions.BadRequestException;
import app.lucas.meusgastos.generic.dto.NameIdResponseDTO;
import app.lucas.meusgastos.people.dto.PeoplePutDTO;
import app.lucas.meusgastos.people.dto.PeopleCreateDTO;
import app.lucas.meusgastos.people.dto.PeopleResponseDTO;
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
@CrossOrigin("*")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping
    public PeopleResponseDTO create(@RequestBody @Valid PeopleCreateDTO peopleDTO) {
        NameIdResponseDTO nameIdResponseDTO = peopleService.create(peopleDTO);
        return new PeopleResponseDTO(HttpStatus.CREATED.value(), new ArrayList<>(Collections.singleton(nameIdResponseDTO)));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody @Valid PeoplePutDTO peoplePutDTO, @PathVariable Long id) {
        if (id.equals(peoplePutDTO.id())) {
            peopleService.update(peoplePutDTO);
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
