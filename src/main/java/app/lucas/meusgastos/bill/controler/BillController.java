package app.lucas.meusgastos.bill.controler;

import app.lucas.meusgastos.bill.dto.BillPostDTO;
import app.lucas.meusgastos.bill.dto.BillRequestDTO;
import app.lucas.meusgastos.bill.dto.BillResponseDTO;
import app.lucas.meusgastos.bill.dto.BillResponseToDate;
import app.lucas.meusgastos.bill.entity.Bill;
import app.lucas.meusgastos.bill.service.BillService;
import app.lucas.meusgastos.user.entity.User;
import app.lucas.meusgastos.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("bill")
public class BillController {

    @Autowired
    private BillService service;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid BillPostDTO billPostDTO) {
        service.save(billPostDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity findAllDataByDate(
            @RequestParam String username,
            @RequestParam String date,
            @RequestParam(required = false) String name
    ) {
        List<BillResponseDTO> billList = service.findAllDataByDate(username, date);
        User user = userRepository.findByUsername(username);

        if (name != null) {
            billList.stream().filter(billResponseDTO -> Objects.equals(billResponseDTO.people(), name));
        }

        BillResponseToDate billResponseToDate = new BillResponseToDate(user.getSalary(), user.getName(), billList);
        return new ResponseEntity(billResponseToDate, HttpStatus.OK);
    }
}
