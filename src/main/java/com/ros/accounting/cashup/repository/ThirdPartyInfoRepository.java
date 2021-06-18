package com.ros.accounting.cashup.repository;

import com.ros.accounting.cashup.model.ThirdPartyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyInfoRepository extends JpaRepository<ThirdPartyInfo, Long> {

}
