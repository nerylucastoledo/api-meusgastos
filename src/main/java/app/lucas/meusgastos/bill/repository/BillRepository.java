package app.lucas.meusgastos.bill.repository;

import app.lucas.meusgastos.bill.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Bill findByUsername(String username);
    List<Bill> findAllByUsername(String username);
    Bill findByDate(String date);
    List<Bill> findAllByDate(String date);
}
