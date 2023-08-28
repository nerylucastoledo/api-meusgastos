package app.lucas.meusgastos.bill.service;

import app.lucas.meusgastos.bill.dto.BillPostDTO;
import app.lucas.meusgastos.bill.dto.BillRequestDTO;
import app.lucas.meusgastos.bill.dto.BillResponseDTO;
import app.lucas.meusgastos.bill.entity.Bill;
import app.lucas.meusgastos.bill.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<BillResponseDTO> findAllDataByDate(String username, String date) {
        List<Bill> allDataByUsername = billRepository.findAllByUsername(username);
        List<Bill> dataByDate = filterDataByDate(date, allDataByUsername);

        List<BillResponseDTO> billList = dataByDate.stream().map(bill -> new BillResponseDTO(
                        bill.getItem(),
                        bill.getValue(),
                        bill.getDescription(),
                        bill.getCard(),
                        bill.getPeople(),
                        bill.getCategory()))
                .collect(Collectors.toList());

        return billList;
    }

    public void save(BillPostDTO billPostDTO) {
        Bill bill = Bill.BillBuilder
                .builder()
                .username(billPostDTO.username())
                .item(billPostDTO.item())
                .value(billPostDTO.value())
                .description(billPostDTO.description())
                .people(billPostDTO.people())
                .category(billPostDTO.category())
                .date(billPostDTO.date())
                .card(billPostDTO.card())
                .build();
        billRepository.save(bill);
    }

    public List<Bill> filterDataByDate(String date, List<Bill> billList) {
        return billList.stream()
                .filter(bill -> bill.getDate().equals(date))
                .collect(Collectors.toList());
    }
}
