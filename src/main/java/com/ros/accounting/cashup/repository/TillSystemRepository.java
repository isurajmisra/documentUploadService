package com.ros.accounting.cashup.repository;

import com.ros.accounting.cashup.model.TillSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TillSystemRepository extends JpaRepository<TillSystem, Long> {

}
