package app.lucas.meusgastos.category.controller;

import app.lucas.meusgastos.category.dto.CategoryPostDTO;
import app.lucas.meusgastos.category.service.CategoryService;
import app.lucas.meusgastos.generic.dto.NameIdDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<NameIdDTO> save(@RequestBody @Valid CategoryPostDTO categoryDTO) {
        return new ResponseEntity(categoryService.save(categoryDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<NameIdDTO> findAll() {
        return categoryService.findAll();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody @Valid NameIdDTO categoryNameIdDTO) {
        categoryService.update(categoryNameIdDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
