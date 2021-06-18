package com.ros.accounting.cashup.controller.dto;

import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.model.CashUpTimeIndicator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewCashUpDto {

    private UUID id;
    private Date cashUpDate;
    private CashUpTimeIndicator cashUpTimeIndicator;
    private float epos;
    private float cash;
    private float pdq;
    private float delivery;
    private float difference;
    private float kpi;
    private CashUpStatus status;
}
