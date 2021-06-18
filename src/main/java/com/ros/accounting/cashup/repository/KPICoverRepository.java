package com.ros.accounting.cashup.repository;

import com.ros.accounting.cashup.model.KPICover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KPICoverRepository extends JpaRepository<KPICover, Long> {

}
