package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.ClientEntity;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Autowired
    SessionFactory factory;

    public List<ClientEntity> getAll(){
        Session session = factory.openSession();
        List<ClientEntity> list = (List<ClientEntity>) session.createQuery("from ClientEntity C " +
                "order by C.name, C.email, C.phone").list();
        session.close();
        return list;
    }

    public ClientEntity getEntityById(int id){
        Session session = factory.openSession();
        ClientEntity entity = session.get(ClientEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public void save(ClientEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.save(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void update(ClientEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.update(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void delete(ClientEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.delete(entity);
        tc.commit();
        session.close();
    }

}
