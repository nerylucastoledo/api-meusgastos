package app.lucas.meusgastos.bill.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String item;
    private Double value;
    private String description = "";
    private String people;
    private String category;
    private String date;
    private String card;


    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public String getItem() {
        return item;
    }

    public Double getValue() {
        return value;
    }

    public String getPeople() {
        return people;
    }

    public String getCard() {
        return card;
    }

    public String getCategory() {
        return category;
    }

    public static final class BillBuilder {
        private Long id;
        private String username;
        private String item;
        private Double value;
        private String people;
        private String category;
        private String date;
        private String card;

        private BillBuilder() {
        }

        public static BillBuilder builder() {
            return new BillBuilder();
        }

        public BillBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BillBuilder username(String username) {
            this.username = username;
            return this;
        }

        public BillBuilder item(String item) {
            this.item = item;
            return this;
        }

        public BillBuilder value(Double value) {
            this.value = value;
            return this;
        }

        public BillBuilder people(String people) {
            this.people = people;
            return this;
        }

        public BillBuilder category(String category) {
            this.category = category;
            return this;
        }

        public BillBuilder date(String date) {
            this.date = date;
            return this;
        }

        public BillBuilder card(String card) {
            this.card = card;
            return this;
        }

        public Bill build() {
            Bill bill = new Bill();
            bill.id = this.id;
            bill.date = this.date;
            bill.item = this.item;
            bill.people = this.people;
            bill.card = this.card;
            bill.value = this.value;
            bill.category = this.category;
            bill.username = this.username;
            return bill;
        }
    }
}
