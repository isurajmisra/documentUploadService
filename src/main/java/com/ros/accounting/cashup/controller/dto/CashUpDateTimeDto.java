/**
 *
 */
package com.ros.accounting.cashup.controller.dto;

import com.ros.accounting.cashup.model.CashUpTimeIndicator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author debad
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashUpDateTimeDto {

    private Date cashUpDate;

    private CashUpTimeIndicator cashUpTimeIndicator;

}
