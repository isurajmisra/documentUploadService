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
public class CardReconciliationInfoDto {

    private UUID id;

    private CashnPDQDto cashnpdqdto;

    private float reconCardManualAmount;

    private float reconCardAPIAmount;

    private ReconciliationMatchTypeDto reconciliationMatchTypeDto;
}
