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
    void deleteAllByPeopleAndUsername(String people, String username);
    void deleteAllByCardAndUsername(String card, String username);
    void deleteAllByCategoryAndUsername(String category, String username);
    @Modifying
    @Query("UPDATE Bill b SET b.card = :newValue WHERE b.card = :lastValue AND b.username LIKE :username")
    void updateAllByCard(@Param("lastValue") String lastValue, @Param("newValue") String newValue, @Param("username") String username);
    @Modifying
    @Query("UPDATE Bill b SET b.category = :newValue WHERE b.category LIKE :lastValue AND b.username LIKE :username")
    void updateAllByCategory(@Param("lastValue") String lastValue, @Param("newValue") String newValue, @Param("username") String username);
    @Modifying
    @Query("UPDATE Bill b SET b.people = :newValue WHERE b.people = :lastValue AND b.username LIKE :username")
    void updateAllByPeople(@Param("lastValue") String lastValue, @Param("newValue") String newValue, @Param("username") String username);
    List<Bill> findAllByDate(String date);
}
