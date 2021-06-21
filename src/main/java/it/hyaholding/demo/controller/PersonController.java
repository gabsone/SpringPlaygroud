package it.hyaholding.demo.controller;


import it.hyaholding.demo.entity.Person;
import it.hyaholding.demo.entity.Sex;
import it.hyaholding.demo.exception.PermissionDeniedException;
import it.hyaholding.demo.service.PersonService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;

@RestController("/api")
@Log
public class PersonController {

    private final PersonService personService;


    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getallpersonbysex")
    public ResponseEntity<List<Person>> getAllPersonBySex(@RequestParam("sex") Sex sex)  {
        log.log(Level.INFO,"Sto invocando getAllPersonBySex");

        try {
            return  ResponseEntity.ok(personService.getAllBySex(sex));
        }catch (PermissionDeniedException pde){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }


}
