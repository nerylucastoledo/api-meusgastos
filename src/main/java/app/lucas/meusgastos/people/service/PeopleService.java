package app.lucas.meusgastos.people.service;

import app.lucas.meusgastos.people.dto.PeopleDTO;
import app.lucas.meusgastos.people.entity.People;
import app.lucas.meusgastos.people.repository.PeopleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
