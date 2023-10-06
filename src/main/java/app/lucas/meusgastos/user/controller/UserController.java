package app.lucas.meusgastos.user.controller;

import app.lucas.meusgastos.bill.dto.ResponseErrorApiDTO;
import app.lucas.meusgastos.exceptions.BadRequestException;
import app.lucas.meusgastos.generic.dto.NameIdResponseDTO;
import app.lucas.meusgastos.user.dto.*;
import app.lucas.meusgastos.user.entity.User;
import app.lucas.meusgastos.user.repository.UserRepository;
import app.lucas.meusgastos.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/create")
    public ResponseEntity<NameIdResponseDTO> create(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return new ResponseEntity(userService.create(userCreateDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
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
                    new UserResponseDTO(userEmailExists.getUsername(), userEmailExists.getId()),
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
    public ResponseEntity updateSalary(@RequestBody @Valid UserPutSalaryDTO userPutSalaryDTO, @PathVariable Long id) {
        if (id.equals(userPutSalaryDTO.id())) {
            userService.updateSalary(userPutSalaryDTO);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            throw new BadRequestException("ID da url diferente do contéudo");
        }
    }

    @PutMapping(path = "reset-password")
    public ResponseEntity updatePassword(@RequestBody @Valid UserPutPasswordDTO userPutPasswordDTO) {
        userService.updatePassword(userPutPasswordDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity getUsernameByEmail(@PathVariable String email) {
        User userEmailExists = userRepository.findByEmail(email);

        if (userEmailExists == null) {
            return new ResponseEntity(new ResponseErrorApiDTO(
                    "E-mail não encontrado!",
                    HttpStatus.UNAUTHORIZED.value(),
                    true
            ), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(
                new UserResponseDTO(userEmailExists.getUsername(), userEmailExists.getId()),
                HttpStatus.OK
        );
    }
}
