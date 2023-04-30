package ru.daniil.springcourse.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.daniil.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;



    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index(){
       return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(name,age,email) VALUES(?,?,?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name =?, age =?, email =? WHERE id =?", updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id =?", id);
    }




    //////////////////////////////
    ///Тестируем производительность пакетной вставки
    //////////////////////////////

    public void testMultipleUpdate(){
        List <Person> people =  create1000People();

        long before = System.currentTimeMillis();

        for(Person person : people){
            jdbcTemplate.update("INSERT INTO person(id,name,age,email) VALUES(?,?,?,?)",person.getId(), person.getName(), person.getAge(), person.getEmail());

        }
        long after = System.currentTimeMillis();

        System.out.println("Time: "+(after-before));
    }

    public void testBatchUpdate(){
        List<Person> persons = create1000People();
        long before = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO person VALUES(?,?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,persons.get(i).getId());
                ps.setString(2,persons.get(i).getName());
                ps.setInt(3,persons.get(i).getAge());
                ps.setString(4,persons.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return persons.size();
            }
        });
        long after = System.currentTimeMillis();

        System.out.println("Time: "+(after-before));
    }

    private List<Person> create1000People(){
        List<Person> result = new ArrayList<Person>();
        for(int i = 0; i < 1000; i++){
            result.add(new Person(i,"Name"+i,"test"+i+"@mail.ru",30));
        }
        return result;
    }
}
