package app.lucas.meusgastos.category.controller;

import app.lucas.meusgastos.card.dto.CardResponseApiDTO;
import app.lucas.meusgastos.category.dto.CategoryPostDTO;
import app.lucas.meusgastos.category.dto.CategoryPutDTO;
import app.lucas.meusgastos.category.dto.CategoryResponseApiDTO;
import app.lucas.meusgastos.category.service.CategoryService;
import app.lucas.meusgastos.exceptions.BadRequestException;
import app.lucas.meusgastos.generic.dto.NameIdDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponseApiDTO save(@RequestBody @Valid CategoryPostDTO categoryDTO) {
        NameIdDTO nameIdDTO = categoryService.save(categoryDTO);
        return new CategoryResponseApiDTO(HttpStatus.CREATED.value(), new ArrayList<>(Collections.singleton(nameIdDTO)));
    }

    @GetMapping
    public CategoryResponseApiDTO findAll(@RequestParam String username) {
        if (username.isEmpty()) {
            throw new BadRequestException("Username não pode ser vazio");
        }

        List<NameIdDTO> nameIdDTOList = categoryService.findAll(username);
        return new CategoryResponseApiDTO(HttpStatus.OK.value(), nameIdDTOList);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody @Valid CategoryPutDTO categoryPutDTO, @PathVariable Long id) {
        if (id.equals(categoryPutDTO.id())) {
            categoryService.update(categoryPutDTO);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            throw new BadRequestException("ID da url diferente do contéudo");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
