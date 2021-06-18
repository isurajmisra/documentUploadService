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
public class KPIDto {
    private List<KpiCoverDto> kpiCovers;

    private List<ComplaintDto> complaints;

    private List<BreakDownDto> breakDownDetails;
}
