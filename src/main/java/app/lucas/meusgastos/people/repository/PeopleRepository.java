package app.lucas.meusgastos.people.repository;

import app.lucas.meusgastos.people.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Long> {
    List<People> findAllByUsername(String username);
    void deleteByNameAndUsername(String name, String username);
}
