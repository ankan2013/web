package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.FilmEntity;

import java.util.List;

public class FilmDAOImpl implements FilmDAO {

    @Autowired
    SessionFactory factory;

    public List<FilmEntity> getAll(){
        Session session = factory.openSession();
        List<FilmEntity> list = (List<FilmEntity>) session.createQuery("from FilmEntity F " +
                "order by F.name, F.info").list();
        session.close();
        return list;
    }

    public FilmEntity getEntityById(int id){
        Session session = factory.openSession();
        FilmEntity entity = session.get(FilmEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public void save(FilmEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.save(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void update(FilmEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.update(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void delete(FilmEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.delete(entity);
        tc.commit();
        session.close();
    }

}
