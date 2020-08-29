package spring.dao;

import spring.model.OrdrEntity;

import java.util.List;

public interface OrdrDAO {
    public List<OrdrEntity> getAll();
    public List<OrdrEntity> getActiveOrdrs();
    public OrdrEntity getEntityById(int id);
    public void save(OrdrEntity entity);
    public void update(OrdrEntity entity);
    public void delete(OrdrEntity entity);
}
