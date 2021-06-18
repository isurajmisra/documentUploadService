package com.ros.accounting.cashup.repository;

import com.ros.accounting.cashup.model.BreakDown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BreakDownRepository extends JpaRepository<BreakDown, Long> {

}
