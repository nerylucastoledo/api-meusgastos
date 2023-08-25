package app.lucas.meusgastos.category.service;

import app.lucas.meusgastos.category.dto.CategoryNameDTO;
import app.lucas.meusgastos.category.dto.CategoryNameIdDTO;
import app.lucas.meusgastos.category.dto.CategoryPostDTO;
import app.lucas.meusgastos.category.entity.Category;
import app.lucas.meusgastos.category.repository.CategoryRepository;
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
    public CategoryNameDTO save(CategoryPostDTO categoryDTO) {
        Category category = Category.CategoryBuilder
                .builder()
                .name(categoryDTO.name())
                .username(categoryDTO.username())
                .build();

        categoryRepository.save(category);
        return new CategoryNameDTO(categoryDTO.name());
    }

    public List<CategoryNameIdDTO> findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryNameIdDTO> categoryResponseAllDTOList = new ArrayList<>();

        for (Category category : categoryList) {
            CategoryNameIdDTO categoryNameIdDTO = new CategoryNameIdDTO(
                    category.getId(),
                    category.getName());
            categoryResponseAllDTOList.add(categoryNameIdDTO);
        }

        return categoryResponseAllDTOList;
    }

    public void delete(Long id) {
        Category category = findBYIdOrThrowError(id);
        categoryRepository.delete(category);
    }

    public void update(CategoryNameIdDTO categoryNameIdDTO) {
        Category savedCategory = findBYIdOrThrowError(categoryNameIdDTO.id());
        savedCategory.setName(categoryNameIdDTO.name());
        categoryRepository.save(savedCategory);
    }

    private Category findBYIdOrThrowError(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow();
    }
}
