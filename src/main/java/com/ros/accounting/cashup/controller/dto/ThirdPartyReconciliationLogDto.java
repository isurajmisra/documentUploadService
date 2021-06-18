/**
 *
 */
package com.ros.accounting.cashup.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Ayush
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartyReconciliationLogDto {

    private Date cashUpDate;

    private String sheetStatus;

    private String thirdPartyName;

}
