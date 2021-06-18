package com.ros.accounting.cashup.controller.dto;

import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.model.CashUpTimeIndicator;
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
public class CashUpDto {

    private UUID id;

    private Date cashUpDate;

    private CashUpStatus cashUpStatus;

    private CashUpTimeIndicator cashUpTimeIndicator;

    private List<SalesDto> sales;

    private CashnPDQDto cashnPdq;

    private List<ThirdPartyInfoDto> thirdPartyInfo;

    private KPIDto kpi;

    private SafeSummaryDto safeSummary;

    private String reason;

    private String reasonAddedBy;

    private UUID restaurantId;

    private float EPOStotal;

    private float CASHtotal;

    private float PDQtotal;

    private float deliverytotal;

    private float KPITotal;
}
