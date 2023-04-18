package ru.daniil.springcourse.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.daniil.springcourse.models.Person;

public class Test {
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PersonDAO personDAO = new PersonDAO(jdbcTemplate);
        System.out.println(personDAO.index());
    }
}
