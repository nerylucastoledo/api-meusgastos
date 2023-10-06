package app.lucas.meusgastos.user.service;

import app.lucas.meusgastos.exceptions.BadRequestException;
import app.lucas.meusgastos.generic.dto.NameIdResponseDTO;
import app.lucas.meusgastos.user.dto.UserCreateDTO;
import app.lucas.meusgastos.user.dto.UserPutSalaryDTO;
import app.lucas.meusgastos.user.dto.UserPutPasswordDTO;
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
    public NameIdResponseDTO create(UserCreateDTO userCreateDTO) {
        User user = User.UserBuilder
                .builder()
                .name(userCreateDTO.name())
                .lastname(userCreateDTO.lastname())
                .salary(userCreateDTO.salary())
                .email(userCreateDTO.email())
                .password(userCreateDTO.password())
                .build();

        userRepository.save(user);
        return new NameIdResponseDTO(user.getId(), userCreateDTO.name());
    }

    public void updateSalary(UserPutSalaryDTO userPutSalaryDTO) {
        User user = findBYIdOrThrowError(userPutSalaryDTO.id());
        user.setSalary(userPutSalaryDTO.salary());
        userRepository.save(user);
    }

    public void updatePassword(UserPutPasswordDTO userPutPasswordDTO) {
        User user = userRepository.findByUsername(userPutPasswordDTO.username());
        user.setPassword(userPutPasswordDTO.password());
        userRepository.save(user);
    }

    private User findBYIdOrThrowError(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado"));
    }
}
