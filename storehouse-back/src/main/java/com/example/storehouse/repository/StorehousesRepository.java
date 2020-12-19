package com.example.storehouse.repository;

import com.example.storehouse.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface StorehousesRepository extends JpaRepository<Storehouse, Integer> {

}
