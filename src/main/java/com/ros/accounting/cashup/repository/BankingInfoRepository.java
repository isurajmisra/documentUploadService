/**
 *
 */
package com.ros.accounting.cashup.repository;

import com.ros.accounting.cashup.model.BankingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author debad
 *
 */
@Repository
public interface BankingInfoRepository extends JpaRepository<BankingInfo, UUID> {

    @Query(value = "select b.bankingTotal,b.bankedTotal from BankingInfo b")
    List<BankingInfo> findVarience();

    @Query(value = "select b from BankingInfo b where"
            + " b.bankingDate between :fromDate and :toDate")
    List<BankingInfo> findReconciliationSheets(@Param("fromDate") Date fromDate,
                                               @Param("toDate") Date toDate);
}
