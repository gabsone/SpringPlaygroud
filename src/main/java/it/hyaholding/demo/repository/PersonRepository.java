package it.hyaholding.demo.repository;

import it.hyaholding.demo.entity.Person;
import it.hyaholding.demo.entity.Sex;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends MongoRepository<Person,String> {

    List<Person> findAllBySex(Sex sex);


}
