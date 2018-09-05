package dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import pojo.Address;

public class AddressDAO extends AbstractDAO<Address> {

    public AddressDAO(SessionFactory factory){
        super(factory);
    }

    public Address findById(long id){
        return get(id);
    }

    public Address create(Address address){
        return persist(address);
    }
}
