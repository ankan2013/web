package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.DiskEntity;

import java.util.List;

public class DiskDAOImpl implements DiskDAO {

    @Autowired
    SessionFactory factory;

    public List<DiskEntity> getAll() {
        Session session = factory.openSession();
        List<DiskEntity> list = (List<DiskEntity>) session.createQuery("from DiskEntity D " +
                "order by D.name, D.type, D.price").list();
        session.close();
        return list;
    }

    public DiskEntity getEntityById(int id) {
        Session session = factory.openSession();
        DiskEntity entity = session.get(DiskEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public void save(DiskEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.save(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void update(DiskEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.update(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void delete(DiskEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.delete(entity);
        tc.commit();
        session.close();
    }
}