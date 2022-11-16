package com.toystore.repository;

import org.springframework.data.repository.CrudRepository;

import com.toystore.entity.Delivery;

public interface IDeliveryRepository extends CrudRepository <Delivery, Long> {

}
