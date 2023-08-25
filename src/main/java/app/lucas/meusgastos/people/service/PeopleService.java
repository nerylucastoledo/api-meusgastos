package app.lucas.meusgastos.people.service;

import app.lucas.meusgastos.people.dto.PeopleDTO;
import app.lucas.meusgastos.people.dto.PeopleNameIdDTO;
import app.lucas.meusgastos.people.entity.People;
import app.lucas.meusgastos.people.repository.PeopleRepository;
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
    public PeopleDTO save(PeopleDTO peopleDTO) {
        People people = People.PeopleBuilder
                .builder()
                .name(peopleDTO.name())
                .username(peopleDTO.username())
                .build();

        peopleRepository.save(people);
        return peopleDTO;
    }

    public List<PeopleNameIdDTO> findAll() {
        List<People> peopleList = peopleRepository.findAll();
        List<PeopleNameIdDTO> peopleResponseAllDTOList = new ArrayList<>();

        for (People people : peopleList) {
            PeopleNameIdDTO peopleResponseAllDTO = new PeopleNameIdDTO(
                    people.getId(),
                    people.getName());
            peopleResponseAllDTOList.add(peopleResponseAllDTO);
        }
        return peopleResponseAllDTOList;
    }

    public void update(PeopleNameIdDTO peopleNameIdDTO) {
        People savedPeople = findBYIdOrThrowError(peopleNameIdDTO.id());
        savedPeople.setName(peopleNameIdDTO.name());
        peopleRepository.save(savedPeople);
    }

    public void delete(Long id) {
        People people = findBYIdOrThrowError(id);
        peopleRepository.delete(people);
    }

    private People findBYIdOrThrowError(Long id) {
        return peopleRepository.findById(id)
                .orElseThrow();
    }
}
