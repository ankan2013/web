package spring.dao;

import spring.model.FilmEntity;

import java.util.List;

public interface FilmDAO {
    public List<FilmEntity> getAll();
    public FilmEntity getEntityById(int id);
    public void save(FilmEntity entity);
    public void update(FilmEntity entity);
    public void delete(FilmEntity entity);
}
