package app.lucas.meusgastos.people.service;

import app.lucas.meusgastos.exceptions.BadRequestException;
import app.lucas.meusgastos.generic.dto.NameIdDTO;
import app.lucas.meusgastos.people.dto.PeoplePutDTO;
import app.lucas.meusgastos.people.dto.PeopleRequestDTO;
import app.lucas.meusgastos.people.entity.People;
import app.lucas.meusgastos.people.repository.PeopleRepository;
import app.lucas.meusgastos.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Transactional
    public NameIdDTO save(PeopleRequestDTO peopleDTO) {
        People people = People.PeopleBuilder
                .builder()
                .name(peopleDTO.name())
                .username(peopleDTO.username())
                .build();

        peopleRepository.save(people);
        return new NameIdDTO(people.getId(), peopleDTO.name());
    }

    public List<NameIdDTO> findAll(String username) {
        List<People> peopleList = peopleRepository.findAllByUsername(username);
        List<NameIdDTO> peopleResponseList = new ArrayList<>();

        for (People people : peopleList) {
            NameIdDTO peopleNameId = new NameIdDTO(
                    people.getId(),
                    people.getName());
            peopleResponseList.add(peopleNameId);
        }
        return peopleResponseList;
    }

    public void update(PeoplePutDTO peoplePutDTO) {
        People savedPeople = findBYIdOrThrowError(peoplePutDTO.id());

        if (savedPeople.getUsername().equals(peoplePutDTO.username())) {
            savedPeople.setName(peoplePutDTO.name());
            peopleRepository.save(savedPeople);
        }
    }

    public void delete(Long id) {
        People people = findBYIdOrThrowError(id);
        peopleRepository.delete(people);
    }

    private People findBYIdOrThrowError(Long id) {
        return peopleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Nenhuma pessoa encontrada com esse ID"));
    }
}
