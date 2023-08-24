package app.lucas.meusgastos.user.service;

import app.lucas.meusgastos.user.dto.UserDTO;
import app.lucas.meusgastos.user.entity.User;
import app.lucas.meusgastos.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDTO save(UserDTO userDTO) {
        User user = User.UserBuilder
                .builder()
                .name(userDTO.name())
                .lastname(userDTO.lastname())
                .salary(userDTO.salary())
                .email(userDTO.email())
                .password(userDTO.password())
                .build();

        userRepository.save(user);
        return userDTO;
    }
}
