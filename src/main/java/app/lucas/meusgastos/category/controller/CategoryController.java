package app.lucas.meusgastos.category.controller;

import app.lucas.meusgastos.category.dto.CategoryCreateDTO;
import app.lucas.meusgastos.category.dto.CategoryPutDTO;
import app.lucas.meusgastos.category.dto.CategoryResponseDTO;
import app.lucas.meusgastos.category.service.CategoryService;
import app.lucas.meusgastos.exceptions.BadRequestException;
import app.lucas.meusgastos.generic.dto.NameIdResponseDTO;
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
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponseDTO create(@RequestBody @Valid CategoryCreateDTO categoryDTO) {
        NameIdResponseDTO nameIdResponseDTO = categoryService.create(categoryDTO);
        return new CategoryResponseDTO(HttpStatus.CREATED.value(), new ArrayList<>(Collections.singleton(nameIdResponseDTO)));
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
