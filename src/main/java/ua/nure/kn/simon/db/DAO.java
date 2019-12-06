package ua.nure.kn.simon.db;

import java.util.Collection;

public interface DAO<T> {
    T create(T entity) throws DatabaseException;
    
    void update(T entity) throws DatabaseException;
    
    void delete(T entity) throws DatabaseException;
    
    T find(Long id) throws DatabaseException;
    
    Collection<T> findAll() throws DatabaseException;

    void setConnectionFactory(ConnectionFactory connectionFactory);
}
