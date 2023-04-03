package ru.daniil.springcourse.DAO;

import org.springframework.stereotype.Component;
import ru.daniil.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Tom"));
        people.add(new Person(++PEOPLE_COUNT,"Bob"));
        people.add(new Person(++PEOPLE_COUNT,"Mike"));
        people.add(new Person(++PEOPLE_COUNT,"Mary"));
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

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
}
