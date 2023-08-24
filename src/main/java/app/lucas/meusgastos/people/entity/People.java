package app.lucas.meusgastos.people.entity;

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
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String username;


    public static final class PeopleBuilder {
        private String name;
        private String username;

        private PeopleBuilder() {
        }

        public static PeopleBuilder builder() {
            return new PeopleBuilder();
        }

        public PeopleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PeopleBuilder username(String username) {
            this.username = username;
            return this;
        }

        public People build() {
            People people = new People();
            people.username = this.username;
            people.name = this.name;
            return people;
        }
    }
}
