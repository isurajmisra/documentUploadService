package com.ros.accounting.cashup.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReconciliationInfoDto {

    private UUID id;

    private Date reconciliationDate;

    private boolean isReconciled;

    private List<CashReconciliationInfoDto> cashReconciliationsDto;

    private List<CardReconciliationInfoDto> cardReconciliationsDto;

    private List<CardReconciliationInfoDto> thirdPartyReconciliationsDto;

    private float cardtotals, cashtotals, thirdpartytotals, reconciledTotal;

    private int pendingCard, pendingCash, pendingThirdParty;
}
