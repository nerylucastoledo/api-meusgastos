package app.lucas.meusgastos.category.service;

import app.lucas.meusgastos.category.dto.CategoryPostDTO;
import app.lucas.meusgastos.category.dto.CategoryPutDTO;
import app.lucas.meusgastos.category.entity.Category;
import app.lucas.meusgastos.category.repository.CategoryRepository;
import app.lucas.meusgastos.generic.dto.NameIdDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public NameIdDTO save(CategoryPostDTO categoryDTO) {
        Category category = Category.CategoryBuilder
                .builder()
                .name(categoryDTO.name())
                .username(categoryDTO.username())
                .build();

        categoryRepository.save(category);
        return new NameIdDTO(category.getId(), categoryDTO.name());
    }

    public List<NameIdDTO> findAll(String username) {
        List<Category> categoryList = categoryRepository.findAllByUsername(username);
        List<NameIdDTO> categoryResponseList = new ArrayList<>();

        for (Category category : categoryList) {
            NameIdDTO categoryNameId = new NameIdDTO(
                    category.getId(),
                    category.getName());
            categoryResponseList.add(categoryNameId);
        }
        return categoryResponseList;
    }

    public void delete(Long id) {
        Category category = findBYIdOrThrowError(id);
        categoryRepository.delete(category);
    }

    public void update(CategoryPutDTO categoryPutDTO) {
        Category savedCategory = findBYIdOrThrowError(categoryPutDTO.id());

        if (savedCategory.getUsername().equals(categoryPutDTO.username())) {
            savedCategory.setName(categoryPutDTO.name());
            categoryRepository.save(savedCategory);
        }
    }

    private Category findBYIdOrThrowError(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow();
    }
}
