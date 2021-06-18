package com.ros.accounting.cashup.repository;

import com.ros.accounting.cashup.model.PDQSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDQSystemRepository extends JpaRepository<PDQSystem, Long> {

}
