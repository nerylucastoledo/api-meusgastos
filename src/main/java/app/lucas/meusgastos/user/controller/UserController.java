package app.lucas.meusgastos.user.controller;

import app.lucas.meusgastos.bill.dto.ResponseErrorApiDTO;
import app.lucas.meusgastos.exceptions.BadRequestException;
import app.lucas.meusgastos.generic.dto.NameIdDTO;
import app.lucas.meusgastos.user.dto.UserDTO;
import app.lucas.meusgastos.user.dto.UserLoginDTO;
import app.lucas.meusgastos.user.dto.UserResponseDTO;
import app.lucas.meusgastos.user.dto.UserSalaryDTO;
import app.lucas.meusgastos.user.entity.User;
import app.lucas.meusgastos.user.repository.UserRepository;
import app.lucas.meusgastos.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<NameIdDTO> save(@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity(userService.save(userDTO), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        User userEmailExists = userRepository.findByEmail(userLoginDTO.email());

        if (userEmailExists == null) {
            return new ResponseEntity(new ResponseErrorApiDTO(
                    "E-mail não encontrado!",
                    HttpStatus.UNAUTHORIZED.value(),
                    true
            ), HttpStatus.UNAUTHORIZED);
        }

        String userPassword = userEmailExists.getPassword();
        String currentPassword = userLoginDTO.password();

        if (currentPassword.equals(userPassword)) {
            return new ResponseEntity(
                    new UserResponseDTO(userEmailExists.getUsername()),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity(new ResponseErrorApiDTO(
                "Senha incorreta!",
                HttpStatus.UNAUTHORIZED.value(),
                true
        ), HttpStatus.UNAUTHORIZED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateSalary(@RequestBody @Valid UserSalaryDTO userSalaryDTO, @PathVariable Long id) {
        if (id.equals(userSalaryDTO.id())) {
            userService.updateSalary(userSalaryDTO);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            throw new BadRequestException("ID da url diferente do contéudo");
        }
    }
}
