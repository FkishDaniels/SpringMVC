package ru.daniil.springcourse.DAO;

import org.springframework.stereotype.Component;
import ru.daniil.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(0,"Tom"));
        people.add(new Person(1,"Bob"));
        people.add(new Person(2,"Mike"));
        people.add(new Person(3,"Mary"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){

        for (Person pers :people) {
            if(pers.getId() == id) return pers;
        }

        return null;
    }
}
