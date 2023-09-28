package app.lucas.meusgastos.user.service;

import app.lucas.meusgastos.exceptions.BadRequestException;
import app.lucas.meusgastos.generic.dto.NameIdDTO;
import app.lucas.meusgastos.people.entity.People;
import app.lucas.meusgastos.user.dto.UserDTO;
import app.lucas.meusgastos.user.dto.UserSalaryDTO;
import app.lucas.meusgastos.user.dto.UserUpdatePasswordPost;
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
    public NameIdDTO save(UserDTO userDTO) {
        User user = User.UserBuilder
                .builder()
                .name(userDTO.name())
                .lastname(userDTO.lastname())
                .salary(userDTO.salary())
                .email(userDTO.email())
                .password(userDTO.password())
                .build();

        userRepository.save(user);
        return new NameIdDTO(user.getId(), userDTO.name());
    }

    public void updateSalary(UserSalaryDTO userSalaryDTO) {
        User user = findBYIdOrThrowError(userSalaryDTO.id());
        user.setSalary(userSalaryDTO.salary());
        userRepository.save(user);
    }

    public void updatePassword(UserUpdatePasswordPost userUpdatePasswordPost) {
        User user = userRepository.findByUsername(userUpdatePasswordPost.username());
        user.setPassword(userUpdatePasswordPost.password());
        userRepository.save(user);
    }

    private User findBYIdOrThrowError(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado"));
    }
}
