package app.lucas.meusgastos.bill.repository;

import app.lucas.meusgastos.bill.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Bill findByUsername(String username);
    List<Bill> findAllByUsername(String username);
    @Query("SELECT b FROM Bill b WHERE b.username = :username AND SUBSTRING(b.date, LENGTH(b.date) - 3) = :year")
    List<Bill> findAllByDateContainingYearAndUsername(@Param("username") String username, @Param("year") String year);
    @Query("SELECT b FROM Bill b WHERE b.username LIKE :username AND b.card LIKE :card AND b.date LIKE :date")
    List<Bill> findAllByDateContainingYearCardAndUsername(@Param("username") String username, @Param("card") String card, @Param("date") String date);
    Bill findByDate(String date);
    void deleteAllByPeople(String people);
    void deleteAllByCard(String card);
    void deleteAllByCategory(String category);
    @Modifying
    @Query("UPDATE Bill b SET b.card = :newValue WHERE b.card = :lastValue")
    void updateAllByCard(@Param("lastValue") String lastValue, @Param("newValue") String newValue);
    @Modifying
    @Query("UPDATE Bill b SET b.category = :newValue WHERE b.category = :lastValue")
    void updateAllByCategory(@Param("lastValue") String lastValue, @Param("newValue") String newValue);
    @Modifying
    @Query("UPDATE Bill b SET b.people = :newValue WHERE b.people = :lastValue")
    void updateAllByPeople(@Param("lastValue") String lastValue, @Param("newValue") String newValue);
    List<Bill> findAllByDate(String date);
}
