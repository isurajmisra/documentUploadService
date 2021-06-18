package com.ros.accounting.cashup.service;

import com.ros.accounting.cashup.controller.dto.ReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.ReconciliationLogDto;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.model.CashUpStatus;

import java.util.Date;
import java.util.List;

public interface ReconciliationService {

    List<ReconciliationLogDto> reconcileCashUps(List<ReconciliationLogDto> reconciliationLogs) throws cashUpNotFoundException;

    //List<?> getPendingReconciliationSummary(CashUpStatus status, Date fromDate, Date toDate) throws cashUpNotFoundException;

    ReconciliationInfoDto getPendingReconciliationSummary(Date fromDate, Date toDate) throws cashUpNotFoundException;

    ReconciliationLogDto reconcileCashUp(ReconciliationLogDto reconciliationLog) throws cashUpNotFoundException;

    List<ReconciliationLogDto> getCashUps(CashUpStatus status, Date fromDate, Date toDate) throws cashUpNotFoundException;

    ReconciliationInfoDto getReconciledSummary(Date fromDate, Date toDate) throws cashUpNotFoundException;


    ReconciliationLogDto getReconciliationLog(Date fromDate, Date toDate, String reconType)
            throws cashUpNotFoundException;
}
