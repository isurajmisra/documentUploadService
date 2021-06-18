package com.ros.accounting.cashup.repository;

import com.ros.accounting.cashup.model.BreakDownDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreakDownDocumentRepository extends JpaRepository<BreakDownDocument, Long> {

}
