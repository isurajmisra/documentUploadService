package com.ros.accounting.cashup.controller.dto;

import com.ros.accounting.cashup.model.ComplaintReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintDto {
    private ComplaintReason complaintName;

    private String description;

}
