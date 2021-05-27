package com.tmdt.business;

import java.util.List;

public interface GeneralDAO<T> {
	List<T> findAll();
	T save(T t);
	T findOneById(long id);
	void delete(long id);
	T update(long id,T t);

}
