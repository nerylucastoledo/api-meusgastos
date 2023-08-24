package app.lucas.meusgastos.card.entity;

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
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String username;
    private String color;

    public static final class CardBuilder {
        private String name;
        private String username;
        private String color;

        private CardBuilder() {
        }

        public static CardBuilder builder() {
            return new CardBuilder();
        }

        public CardBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CardBuilder username(String username) {
            this.username = username;
            return this;
        }

        public CardBuilder color(String color) {
            this.color = color;
            return this;
        }

        public Card build() {
            Card card = new Card();
            card.name = this.name;
            card.username = this.username;
            card.color = this.color;
            return card;
        }
    }
}
