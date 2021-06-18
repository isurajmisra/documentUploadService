/**
 *
 */
package com.ros.accounting.cashup.controller.dto;

import com.ros.accounting.cashup.model.BankingTimeIndicator;
import com.ros.accounting.cashup.model.CashUpTimeIndicator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author debadutta
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankingInfoDto {

    private UUID id;

    private Date bankingDate;

    private BankingTimeIndicator bankingTimeIndicator;

    private int giroSlipNumber;

    private float bankingTotal;

    private float bankedTotal;

    private String reason;

    private List<Date> cashUpDates;

    private List<CashUpTimeIndicator> cashUpTimeIndicators;

    private int noOfcashUps;

    private String sealedBy;

}
