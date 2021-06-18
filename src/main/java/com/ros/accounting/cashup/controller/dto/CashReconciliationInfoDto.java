package com.ros.accounting.cashup.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashReconciliationInfoDto {

    private UUID id;

    private float reconCashAmount;

    private CashnPDQDto cashnpdqDto;

    private ReconciliationMatchTypeDto reconciliationMatchTypeDto;
}
