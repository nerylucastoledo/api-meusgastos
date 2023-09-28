package app.lucas.meusgastos.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Random;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String lastname;
    private String username;
    private Double salary;
    private String email;
    private String password;

    public static final class UserBuilder {
        private String name;
        private String lastname;
        private Double salary;
        private String email;
        private String password;

        private UserBuilder() {
        }

        public static UserBuilder builder() {
            return new UserBuilder();
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserBuilder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            Random random = new Random();
            User user = new User();
            user.salary = this.salary;
            user.password = this.password;
            user.name = this.name;
            user.email = this.email;
            user.lastname = this.lastname;
            user.username = this.name + this.lastname + random.nextInt(1000000);
            return user;
        }
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
