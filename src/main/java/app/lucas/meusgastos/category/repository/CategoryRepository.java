package app.lucas.meusgastos.category.repository;

import app.lucas.meusgastos.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByUsername(String username);
}
