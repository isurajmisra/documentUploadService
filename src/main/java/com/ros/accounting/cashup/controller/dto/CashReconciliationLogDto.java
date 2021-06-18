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
 * @author ayush.negi
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashReconciliationLogDto {

//	private String sheetStatus;

    private Date date;

    private String sheetStatus;

    private float bankedTotal;

}
