package app.lucas.meusgastos.card.repository;

import app.lucas.meusgastos.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByUsername(String username);
}
