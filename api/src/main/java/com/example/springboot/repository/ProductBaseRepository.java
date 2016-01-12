package com.example.springboot.repository;

import com.example.springboot.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductBaseRepository<T extends Product> extends CrudRepository<T, Long> {

    public T findById(Integer id);

}
