package com.fpmislata.banco.persistencia;

import java.util.List;


public interface GenericDAO <T>{
    public T get(int id);
    public T insert(T t) throws BussinessException;
    public void delete(int id);
    public T update(T t) throws BussinessException;
    public List<T> findAll();
}
