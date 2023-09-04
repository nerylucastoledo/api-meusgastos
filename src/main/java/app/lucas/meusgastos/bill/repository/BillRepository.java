package app.lucas.meusgastos.bill.repository;

import app.lucas.meusgastos.bill.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Bill findByUsername(String username);
    List<Bill> findAllByUsername(String username);
    @Query("SELECT b FROM Bill b WHERE b.username = :username AND SUBSTRING(b.date, LENGTH(b.date) - 3) = :year")
    List<Bill> findAllByDateContainingYearAndUsername(@Param("username") String username, @Param("year") String year);
    Bill findByDate(String date);
    List<Bill> findAllByDate(String date);
}
