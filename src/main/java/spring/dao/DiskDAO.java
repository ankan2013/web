package spring.dao;

import spring.model.DiskEntity;

import java.util.List;

public interface DiskDAO {
    public List<DiskEntity> getAll();
    public DiskEntity getEntityById(int id);
    public void save(DiskEntity entity);
    public void update(DiskEntity entity);
    public void delete(DiskEntity entity);
}
