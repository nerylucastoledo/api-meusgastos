package app.lucas.meusgastos.category.service;

import app.lucas.meusgastos.bill.service.BillService;
import app.lucas.meusgastos.category.dto.CategoryCreateDTO;
import app.lucas.meusgastos.category.dto.CategoryPutDTO;
import app.lucas.meusgastos.category.entity.Category;
import app.lucas.meusgastos.category.repository.CategoryRepository;
import app.lucas.meusgastos.generic.dto.NameIdResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BillService billService;

    @Transactional
    public NameIdResponseDTO create(CategoryCreateDTO categoryDTO) {
        Category category = Category.CategoryBuilder
                .builder()
                .name(categoryDTO.name())
                .username(categoryDTO.username())
                .build();

        categoryRepository.save(category);
        return new NameIdResponseDTO(category.getId(), categoryDTO.name());
    }

    @Transactional
    public void delete(Long id) {
        Category category = findBYIdOrThrowError(id);
        billService.deleteAllByCategory(category.getName(), category.getUsername());
        categoryRepository.deleteByNameAndUsername(category.getName(), category.getUsername());
    }

    @Transactional
    public void update(CategoryPutDTO categoryPutDTO) {
        Category savedCategory = findBYIdOrThrowError(categoryPutDTO.id());

        if (savedCategory.getUsername().equals(categoryPutDTO.username())) {
            billService.updateAllByCategory(savedCategory.getName(), categoryPutDTO.name(), savedCategory.getUsername());

            savedCategory.setName(categoryPutDTO.name());
            categoryRepository.save(savedCategory);
        }
    }

    private Category findBYIdOrThrowError(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow();
    }
}
