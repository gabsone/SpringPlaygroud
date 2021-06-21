package it.hyaholding.demo.service;

import it.hyaholding.demo.entity.Person;
import it.hyaholding.demo.entity.Sex;
import it.hyaholding.demo.exception.PermissionDeniedException;
import it.hyaholding.demo.repository.PersonRepository;
import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureDataMongo
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Log
class PersonServiceTest {

    Person person;

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        person =  new Person();
        person.setName("Gabriele");
        person.setSurname("Ciliberti");
        person.setAddress("Via roma,12");
        person.setSex(Sex.MALE);


    }
    @AfterEach
    public void tearDown() {
        personRepository.deleteAll();
        person = null;
    }


    @Test
    @Order(1)
    public void addPersonTest() {

        person = personRepository.save(person);
        Person fetchedPerson = personRepository.findById(person.getId()).get();
        assertEquals(person.getId(), fetchedPerson.getId());

    }
    @Test
    @Order(2)
    public void getAllBySexExceptionTest() {

        LocalTime now = LocalTime.now();
        LocalTime limit = LocalTime.now().withHour(18).withMinute(15).withSecond(0);
        if(now.isAfter(limit)) {
            assertDoesNotThrow(()->{personService.getAllBySex(Sex.MALE);});
        }else{
            assertThrows(PermissionDeniedException.class,()->{personService.getAllBySex(Sex.MALE);});
        }
    }
    @Test
    @Order(3)
    public void getAllBySexQueryTest() {
        person = personRepository.save(person);
        log.info(String.format("TOTAL:%d", personRepository.findAll().size()));
        LocalTime now = LocalTime.now();
        LocalTime limit = LocalTime.now().withHour(18).withMinute(15).withSecond(0);
        if(now.isAfter(limit)) {
            try {
                List<Person> males = personService.getAllBySex(Sex.MALE);
                log.info(String.format("MALES:%d", personRepository.findAll().size()));
                assertFalse(males.isEmpty());
                assertTrue(males.size() == 1);

            } catch (PermissionDeniedException e) {

            }

        }else{
            assertThrows(PermissionDeniedException.class,()->{personService.getAllBySex(Sex.MALE);});
        }
    }

}