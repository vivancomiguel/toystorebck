package com.toystore.repository;

import org.springframework.data.repository.CrudRepository;

import com.toystore.entity.Order;

public interface IOrderRepository extends CrudRepository <Order, Long> {

}