package app.lucas.meusgastos.category.controller;

import app.lucas.meusgastos.category.dto.CategoryDTO;
import app.lucas.meusgastos.category.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid CategoryDTO categoryDTO) {
        return new ResponseEntity(categoryService.save(categoryDTO), HttpStatus.CREATED);
    }
}
