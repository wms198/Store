package com.store.store.repository;

import com.store.store.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlRepository extends JpaRepository<Address,Integer> {
}
