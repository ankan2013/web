package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.OrdrEntity;

import java.util.List;

public class OrdrDAOImpl implements OrdrDAO {

    @Autowired
    SessionFactory factory;

    public List<OrdrEntity> getAll() {
        Session session = factory.openSession();
        List<OrdrEntity> list = (List<OrdrEntity>) session.createQuery("from OrdrEntity O " +
                "order by O.isReturned, O.requestTime, O.returnTime").list();
        session.close();
        return list;
    }

    public List<OrdrEntity> getActiveOrdrs(){
        Session session = factory.openSession();
        List<OrdrEntity> list = (List<OrdrEntity>) session.createQuery("from OrdrEntity O " +
                "where O.isReturned = false " +
                "order by O.isReturned, O.requestTime, O.returnTime").list();
        session.close();
        return list;
    }

    public OrdrEntity getEntityById(int id) {
        Session session = factory.openSession();
        OrdrEntity entity = session.get(OrdrEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public void save(OrdrEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.save(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void update(OrdrEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.update(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void delete(OrdrEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.delete(entity);
        tc.commit();
        session.close();
    }
}