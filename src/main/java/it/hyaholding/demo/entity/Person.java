package it.hyaholding.demo.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("person")
@Data
public class Person {

    @Id
    private String id;


    private String name;
    private String surname;
    private String address;

    private Sex sex;


    public String getFullName(){
        return new StringBuilder().append(name).append(" ").append(surname).toString();
    }



}
