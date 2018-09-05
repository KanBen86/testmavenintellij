package dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import pojo.Person;

import java.util.List;

public class PersonDAO extends AbstractDAO<Person> {

    public PersonDAO(SessionFactory factory){
        super(factory);
    }

    public Person findById(long id){
        return get(id);
    }

    public Person create(Person person){
        return persist(person);
    }
}
