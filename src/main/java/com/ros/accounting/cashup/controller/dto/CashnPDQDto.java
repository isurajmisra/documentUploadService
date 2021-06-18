package com.ros.accounting.cashup.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashnPDQDto {

    private List<PettyCashDto> pettyCashs;

    private List<TillSystemDto> tillSystems;

    private List<PDQSystemDto> pdqSystems;

    private List<WageAdvanceDto> wageAdvances;
}
