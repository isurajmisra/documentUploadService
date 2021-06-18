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
public class SafeSummaryDto {

    private float safeTillAmount;

    private int safeCount;

    private float bankedAmount;

    private UUID witnessId;

}
