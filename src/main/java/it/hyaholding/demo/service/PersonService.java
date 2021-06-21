package it.hyaholding.demo.service;


import it.hyaholding.demo.entity.Person;
import it.hyaholding.demo.entity.Sex;
import it.hyaholding.demo.exception.PermissionDeniedException;
import it.hyaholding.demo.repository.PersonRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Log
public class PersonService {

    private final PersonRepository personRepository;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getAllBySex(Sex sex) throws PermissionDeniedException {
        log.info("Sto in getAllBySex");
        LocalTime now = LocalTime.now();
        LocalTime limit = LocalTime.now().withHour(18).withMinute(15).withSecond(0);

        if(now.isAfter(limit)) {
            log.info("Sono passate le 18");
            return personRepository.findAllBySex(sex);
        }else{
            log.info("NON sono passate le 18");
            throw new PermissionDeniedException("Non sono ancora le 18:00");
        }



    }




}
