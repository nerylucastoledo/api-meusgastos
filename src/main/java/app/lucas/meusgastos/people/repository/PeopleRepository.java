package app.lucas.meusgastos.people.repository;

import app.lucas.meusgastos.people.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long> { }
