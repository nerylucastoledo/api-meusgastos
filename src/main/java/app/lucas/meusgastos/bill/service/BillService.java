package app.lucas.meusgastos.bill.service;

import app.lucas.meusgastos.bill.dto.BillPostDTO;
import app.lucas.meusgastos.bill.dto.BillPutDTO;
import app.lucas.meusgastos.bill.dto.BillRequestDTO;
import app.lucas.meusgastos.bill.dto.BillResponseDTO;
import app.lucas.meusgastos.bill.entity.Bill;
import app.lucas.meusgastos.bill.repository.BillRepository;
import app.lucas.meusgastos.card.dto.CardIdNameColorDTO;
import app.lucas.meusgastos.card.dto.CardPutDTO;
import app.lucas.meusgastos.card.entity.Card;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<BillResponseDTO> findAllDataByDate(String username, String date) {
        List<Bill> allDataByUsername = billRepository.findAllByUsername(username);
        List<Bill> filterByDate = allDataByUsername
                .stream().filter(data -> data.getDate().equals(date))
                .collect(Collectors.toList());

        List<BillResponseDTO> billList = filterByDate.stream().map(bill -> new BillResponseDTO(
                        bill.getId(),
                        bill.getItem(),
                        bill.getValue(),
                        bill.getCard(),
                        bill.getPeople(),
                        bill.getCategory(),
                        bill.getDate(),
                        bill.getDescription()))
                .collect(Collectors.toList());

        return billList;
    }

    public List<BillResponseDTO> findAllDataByDateAndCard(String username, String date, String nameCard) {
        List<Bill> allDataByUsername = billRepository.findAllByDateContainingYearCardAndUsername(username, nameCard, date);

        List<BillResponseDTO> billList = allDataByUsername.stream().map(bill -> new BillResponseDTO(
                        bill.getId(),
                        bill.getItem(),
                        bill.getValue(),
                        bill.getCard(),
                        bill.getPeople(),
                        bill.getCategory(),
                        bill.getDate(),
                        bill.getDescription()))
                .collect(Collectors.toList());

        return billList;
    }

    public List<BillResponseDTO> findAll(String username, String year) {
        List<Bill> allDataByUsername = billRepository.findAllByDateContainingYearAndUsername(username, year);

        List<BillResponseDTO> billList = allDataByUsername.stream()
                .filter(bill -> bill.getPeople().equals("Eu"))
                .map(bill -> new BillResponseDTO(
                        bill.getId(),
                        bill.getItem(),
                        bill.getValue(),
                        bill.getCard(),
                        bill.getPeople(),
                        bill.getCategory(),
                        bill.getDate(),
                        bill.getDescription()))
                .collect(Collectors.toList());

        return billList;
    }


    public void save(BillPostDTO billPostDTO) {
        Bill bill = Bill.BillBuilder
                .builder()
                .username(billPostDTO.username())
                .item(billPostDTO.item())
                .description(billPostDTO.description())
                .value(billPostDTO.value())
                .people(billPostDTO.people())
                .category(billPostDTO.category())
                .date(billPostDTO.date())
                .card(billPostDTO.card())
                .build();
        billRepository.save(bill);
    }

    public void saveAll(BillPostDTO[] billPostDTO) {
        List<Bill> billList = new ArrayList<>();

        for (BillPostDTO billPost : billPostDTO) {
            Bill bill = Bill.BillBuilder
                    .builder()
                    .username(billPost.username())
                    .description(billPost.description())
                    .item(billPost.item())
                    .value(billPost.value())
                    .people(billPost.people())
                    .category(billPost.category())
                    .date(billPost.date())
                    .card(billPost.card())
                    .build();
            billList.add(bill);
        }
        billRepository.saveAll(billList);
    }

    @Transactional
    public void update(BillPutDTO billPutDTO) {
        Bill savedBill = findBYIdOrThrowError(billPutDTO.id());

        savedBill.setItem(billPutDTO.item());
        savedBill.setValue(billPutDTO.value());
        billRepository.save(savedBill);
    }

    @Transactional
    public void delete(Long id) {
        Bill bill = findBYIdOrThrowError(id);
        billRepository.delete(bill);
    }

    public void deleteAllByPeople(String people, String username) {
        billRepository.deleteAllByPeopleAndUsername(people, username);
    }

    public void deleteAllByCard(String card, String username) {
        billRepository.deleteAllByCardAndUsername(card, username);
    }

    public void deleteAllByCategory(String category, String username) {
        billRepository.deleteAllByCategoryAndUsername(category, username);
    }

    public void updateAllByCard(String lastValue, String newValue, String username) {
        billRepository.updateAllByCard(lastValue, newValue, username);
    }

    public void updateAllByCategory(String lastValue, String newValue, String username) {
        billRepository.updateAllByCategory(lastValue, newValue, username);
    }

    public void updateAllByPeople(String lastValue, String newValue, String username) {
        billRepository.updateAllByPeople(lastValue, newValue, username);
    }

    public List<Bill> filterDataByDate(String date, List<Bill> billList) {
        return billList.stream()
                .filter(bill -> bill.getDate().equals(date))
                .collect(Collectors.toList());
    }

    private Bill findBYIdOrThrowError(Long id) {
        return billRepository.findById(id)
                .orElseThrow();
    }
}
