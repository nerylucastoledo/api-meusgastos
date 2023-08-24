package app.lucas.meusgastos.category.service;

import app.lucas.meusgastos.category.dto.CategoryDTO;
import app.lucas.meusgastos.category.entity.Category;
import app.lucas.meusgastos.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = Category.CategoryBuilder
                .builder()
                .name(categoryDTO.name())
                .username(categoryDTO.username())
                .build();

        categoryRepository.save(category);
        return categoryDTO;
    }
}
