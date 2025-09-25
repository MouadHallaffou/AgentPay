package repository.interfaces;


public interface GenericRepository <T>{
    void insert(T entity);
    void update(T entity);
    boolean delete(int ID);
}
