package com.ros.accounting.cashup.controller.dto;

import com.ros.accounting.cashup.model.BreakDownReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BreakDownDto {

    private String name;

    private String billNumber;

    private BreakDownReason breakDownReason;

    private float amount;

    private DocumentDto document;

}
