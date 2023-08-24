package app.lucas.meusgastos.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String username;

    public static final class CategoryBuilder {
        private Long id;
        private String name;
        private String username;

        private CategoryBuilder() {
        }

        public static CategoryBuilder builder() {
            return new CategoryBuilder();
        }

        public CategoryBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryBuilder username(String username) {
            this.username = username;
            return this;
        }

        public Category build() {
            Category category = new Category();
            category.id = this.id;
            category.username = this.username;
            category.name = this.name;
            return category;
        }
    }
}
