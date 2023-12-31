package app.lucas.meusgastos.bill.controler;

import app.lucas.meusgastos.bill.dto.*;
import app.lucas.meusgastos.bill.service.BillService;
import app.lucas.meusgastos.card.entity.Card;
import app.lucas.meusgastos.card.repository.CardRepository;
import app.lucas.meusgastos.category.entity.Category;
import app.lucas.meusgastos.category.repository.CategoryRepository;
import app.lucas.meusgastos.exceptions.BadRequestException;
import app.lucas.meusgastos.people.entity.People;
import app.lucas.meusgastos.people.repository.PeopleRepository;
import app.lucas.meusgastos.user.entity.User;
import app.lucas.meusgastos.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bill")
@CrossOrigin("*")
public class BillController {

    @Autowired
    private BillService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(produces = "application/json")
    public ResponseEntity save(@RequestBody @Valid BillPostDTO billPostDTO) {
        service.save(billPostDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity saveAll(@RequestBody @Valid BillPostDTO[] billListPostDTO) {
        service.saveAll(billListPostDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity findAllDataByDate(
            @RequestParam String username,
            @RequestParam(required = false) String date
    ) {

        if (username.isBlank() || date.isBlank()) {
            throw new BadRequestException("Username e date não podem ser vazio");
        }

        List<BillResponseDTO> billList = service.findAllDataByDate(username, date);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadRequestException("Nenhum usuário encontrado com esse username");
        }

        List<Card> cardList = cardRepository.findAllByUsername(username);
        List<People> peopleList = peopleRepository.findAllByUsername(username);
        List<Category> categoryList = categoryRepository.findAllByUsername(username);

        BillResponseToDate billResponse = new BillResponseToDate(user.getSalary(), user.getName(), billList, cardList, peopleList, categoryList);
        return new ResponseEntity(billResponse, HttpStatus.OK);
    }

    @GetMapping("/by-card")
    public ResponseEntity findAllDataByDateUsernameAndCard(
            @RequestParam String username,
            @RequestParam(required = true) String date,
            @RequestParam(required = true) String card
    ) {

        if (username.isBlank() || date.isBlank()) {
            throw new BadRequestException("Username e date não podem ser vazio");
        }

        List<BillResponseDTO> billList = service.findAllDataByDateAndCard(username, date, card);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadRequestException("Nenhum usuário encontrado com esse username");
        }

        BillResponseAllData billResponseAllData = new BillResponseAllData(billList);
        return new ResponseEntity(billResponseAllData, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity filterAll(
            @RequestParam String username,
            @RequestParam String year
    ) {

        if (username.isBlank() || year.isBlank()) {
            throw new BadRequestException("Username e o ano não podem ser vazio");
        }

        List<BillResponseDTO> billList = service.findAll(username, year);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadRequestException("Nenhum usuário encontrado com esse username");
        }

        BillResponseAllData billResponseAllData = new BillResponseAllData(billList);
        return new ResponseEntity(billResponseAllData, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody @Valid BillPutDTO billPutDTO, @PathVariable Long id) {
        if (id.equals(billPutDTO.id())) {
            service.update(billPutDTO);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            throw new BadRequestException("ID da url diferentsde do contéudo");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
