package app.lucas.meusgastos.user.repository;

import app.lucas.meusgastos.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);
}
