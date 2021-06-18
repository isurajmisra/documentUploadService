package com.ros.accounting.cashup.repository;

import com.ros.accounting.cashup.model.SafeSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafeSummaryRepository extends JpaRepository<SafeSummary, Long> {

}
