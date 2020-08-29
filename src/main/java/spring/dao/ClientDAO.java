package spring.dao;

import spring.model.ClientEntity;

import java.util.List;

public interface ClientDAO {
    public List<ClientEntity> getAll();
    public ClientEntity getEntityById(int id);
    public void save(ClientEntity entity);
    public void update(ClientEntity entity);
    public void delete(ClientEntity entity);
}
