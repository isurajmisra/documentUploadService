package com.ros.accounting.cashup.service;

import com.ros.accounting.cashup.controller.dto.BankingInfoDto;
import com.ros.accounting.cashup.controller.dto.CashUpDto;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.model.CashUpStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface CashUpService {

    CashUpDto addNewCashUp(CashUpDto cashUp) throws cashUpNotFoundException;

    CashUpDto getCashUpById(UUID id) throws cashUpNotFoundException;

    Object editCashUp(CashUpDto dto) throws cashUpNotFoundException;

    String deleteCashUp(UUID id) throws cashUpNotFoundException;

    List<?> getCashUpSheets(CashUpStatus status, Date fromDate, Date toDate) throws cashUpNotFoundException;

//	List<?> getCashUpSheets(CashUpStatus status) throws cashUpNotFoundException;

    BankingInfoDto CreateBanking(BankingInfoDto banking) throws cashUpNotFoundException;

    List<BankingInfoDto> getAllBanks() throws cashUpNotFoundException;

    List<CashUpDto> getAllCashUpSheets() throws cashUpNotFoundException;

    BankingInfoDto getBankById(UUID id) throws cashUpNotFoundException;

    BankingInfoDto updateBanking(BankingInfoDto bank) throws cashUpNotFoundException;

    String DeleteBankingById(UUID id) throws cashUpNotFoundException;
}
