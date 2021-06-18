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
public class ThirdPartyReconciliationInfoDto {

    private UUID id;

    private ThirdPartyInfoDto thirdPartyManual;

    private ThirdPartyInfoDto thirdPartyAPI;

    private float reconThirdPartyManualAmount;

    private float reconThirdPartyAPIAmount;

    private ReconciliationMatchTypeDto reconciliationMatchTypeDto;
}
