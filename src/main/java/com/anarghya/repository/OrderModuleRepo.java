package com.anarghya.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anarghya.model.OrderDetailsEntity;

@Repository
public interface OrderModuleRepo extends CrudRepository<OrderDetailsEntity, Integer>{

}
