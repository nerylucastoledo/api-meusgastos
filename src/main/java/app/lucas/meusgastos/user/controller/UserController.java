package app.lucas.meusgastos.user.controller;

import app.lucas.meusgastos.user.dto.UserDTO;
import app.lucas.meusgastos.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity(userService.save(userDTO), HttpStatus.CREATED);
    }
}
