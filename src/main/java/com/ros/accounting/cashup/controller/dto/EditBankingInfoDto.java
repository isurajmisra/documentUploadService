/**
 *
 */
package com.ros.accounting.cashup.controller.dto;

import com.ros.accounting.cashup.model.CashUp;
import com.ros.accounting.cashup.model.CashUpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author debad
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditBankingInfoDto {

    private UUID id;

    private Date bankingDate;

    private int giroSlipNumber;

    private float bankingTotal;

    private float bankedTotal;

    private String reason;

    private List<CashUp> cashUps;

    private CashUpStatus cashupStatus;

}
