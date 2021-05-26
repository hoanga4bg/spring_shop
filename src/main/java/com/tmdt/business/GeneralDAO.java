package com.tmdt.business;

import java.util.List;

public interface GeneralDAO<T> {
	List<T> findAll();
	T save(T t);
	T findOneById(int id);
	void delete(int id);
	T update(int id,T t);

}
