package com.ros.accounting.cashup.repository;

import com.ros.accounting.cashup.model.CashnPDQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashNPdqRepository extends JpaRepository<CashnPDQ, Long> {

}
