package com.ros.accounting.cashup.service.impl;

import com.ros.accounting.cashup.controller.dto.*;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.mapper.RestDtoMapper;
import com.ros.accounting.cashup.model.*;
import com.ros.accounting.cashup.repository.BankingInfoRepository;
import com.ros.accounting.cashup.repository.CashUpRepository;
import com.ros.accounting.cashup.service.CashUpService;
import com.ros.accounting.log.RosLogDebug;
import com.ros.accounting.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CashUpServiceImpl implements CashUpService {

    @Autowired
    private CashUpRepository cashUpRepo;

    @Autowired
    private BankingInfoRepository bankingInfoRepo;

    @Autowired
    private RestDtoMapper restDtoMapper;

    /**
     * Adding cashup to the database
     */
    @Override
    @RosLogDebug
    public CashUpDto addNewCashUp(CashUpDto cashUp) throws cashUpNotFoundException {
        List<CashUp> cashUps = checkCashUpByDate(cashUp.getCashUpDate());

        int amCount = 0, pmCount = 0;

        for (CashUp data : cashUps) {
            if (data.getCashUpTimeIndicator().equals(CashUpTimeIndicator.AM)) {
                amCount++;
            } else if (data.getCashUpTimeIndicator().equals(CashUpTimeIndicator.PM)) {
                pmCount++;
            }
        }

        if (amCount < 1 && cashUp.getCashUpTimeIndicator().equals(CashUpTimeIndicator.AM)
                || pmCount < 1 && cashUp.getCashUpTimeIndicator().equals(CashUpTimeIndicator.PM)) {
            if (cashUps.size() < 2) {
                return saveCashUpData(cashUp);
            } else
                throw new cashUpNotFoundException(Properties.cashUpCreationNotPossible);

        } else
            throw new cashUpNotFoundException(Properties.cashUpCreationNotPossible);
    }

    private CashUpDto saveCashUpData(CashUpDto cashUp) {

        calculateTotal(cashUp);
        CashUp cashUpEntity = restDtoMapper.convertToCashUpEntity(cashUp);
        cashUpEntity.addMetaData();

        updateManualEntry(cashUpEntity);

        return restDtoMapper.convertToCashUpDto(cashUpRepo.save(cashUpEntity));
    }

    private void calculateTotal(CashUpDto cashUp) {

        float eposTotal = 0, cashTotal = 0, pdqTotal = 0, deliveryTotal = 0, KPItotal = 0;

        float voidTotal = 0, breakDownTotal = 0;

        for (SalesDto sale : cashUp.getSales()) {
            eposTotal = sale.getCreditCardTip() + sale.getDrinksPayment() + sale.getFoodPayment()
                    + sale.getOtherPayment() + sale.getServiceCharges() + sale.getTakeAwayPayment();
            for (TaxInfoDto taxInfo : sale.getTaxInfo()) {
                eposTotal = eposTotal + taxInfo.getAmount();
            }
        }
        cashUp.setEPOStotal(eposTotal);

        for (TillSystemDto tillSystemDto : cashUp.getCashnPdq().getTillSystems()) {
            cashTotal += tillSystemDto.getAmount();

        }
        cashUp.setCASHtotal(cashTotal);

        for (PDQSystemDto pdqSystemDto : cashUp.getCashnPdq().getPdqSystems()) {
            pdqTotal += pdqSystemDto.getAmount();

        }
        cashUp.setPDQtotal(pdqTotal);

        for (ThirdPartyInfoDto thirdPartyInfoDto : cashUp.getThirdPartyInfo()) {
            deliveryTotal = deliveryTotal + thirdPartyInfoDto.getAmount();

        }
        cashUp.setDeliverytotal(deliveryTotal);

        for (KpiCoverDto kpiCoverDto : cashUp.getKpi().getKpiCovers()) {
            voidTotal = KPItotal + kpiCoverDto.getAmount();

        }

        for (BreakDownDto breakDownDto : cashUp.getKpi().getBreakDownDetails()) {
            breakDownTotal = KPItotal + breakDownDto.getAmount();

        }
        KPItotal = voidTotal + breakDownTotal;
        cashUp.setKPITotal(KPItotal);

    }

    private void updateManualEntry(CashUp cashUpEntity) {
        cashUpEntity.getSales().forEach(sales -> {
            sales.setCashUpInfoMode(CashUpInfoMode.MANUAL);
        });
        cashUpEntity.getCashnPdq().setCashUpInfoMode(CashUpInfoMode.MANUAL);
        cashUpEntity.getThirdPartyInfo().forEach(info -> {
            info.setCashUpInfoMode(CashUpInfoMode.MANUAL);
        });
    }

    private List<CashUp> checkCashUpByDate(Date cashUpDate) {
        return cashUpRepo.checkCashUpByDate(cashUpDate);
    }

//	private void updateEntityData(CashUpDto cashUp, CashUp cashUpEntity) {
//		for (PettyCashDto pettyCashDto : cashUp.getCashnPdq().getPettyCashs()) {
//			PettyCash pettyCash = restDtoMapper.convertToPettyCashEntity(pettyCashDto);
//			pettyCash.getPettyCashMaster().setName(pettyCashDto.getPettyCashName());
//		}
//		for (TillSystemDto tillDto : cashUp.getCashnPdq().getTillSystems()) {
//			TillSystem till = restDtoMapper.convertToTillCashEntity(tillDto);
//			till.getTillMaster().setName(tillDto.getName());
//		}
//		for (PDQSystemDto pdqDto : cashUp.getCashnPdq().getPdqSystems()) {
//			PDQSystem pdq = restDtoMapper.convertToPdqSystem(pdqDto);
//			pdq.getPdqMachineMaster().setName(pdqDto.getName());
//			pdq.getPdqCardMaster().setName(pdqDto.getCardName());
//		}
//		for (ThirdPartyInfoDto thirdPartyInfoDto : cashUp.getThirdPartyInfo()) {
//			ThirdPartyInfo thirdParty = restDtoMapper.convertToThirdPartyInfo(thirdPartyInfoDto);
//			thirdParty.getThirdPartyInfoMaster().setName(thirdPartyInfoDto.getName());
//		}
//	}

    @Override
    @RosLogDebug
    public CashUpDto getCashUpById(UUID id) throws cashUpNotFoundException {
        Optional<CashUp> cashUpFromDB = cashUpRepo.findById(id);
//		CashUp cashUp = cashUpFromDB.orElseThrow(() -> new cashUpNotFoundException(Properties.cashUpNotFound)); 
        if (cashUpFromDB.isPresent()) {

            restDtoMapper.convertToCashUpDto(cashUpFromDB);
            return restDtoMapper.convertToCashUpDto(cashUpRepo.getOne(id));

        } else
            throw new cashUpNotFoundException(Properties.cashUpNotFound);

    }

    @Override
    @RosLogDebug
    public CashUpDto editCashUp(CashUpDto dto) throws cashUpNotFoundException {

        CashUp cashUp = cashUpRepo.getOne(dto.getId());

        if (cashUp.getCashUpStatus() == CashUpStatus.DRAFT) {

            calculateTotal(dto);
            restDtoMapper.updateCashUp(dto, cashUp);

            return restDtoMapper.convertToCashUpDto(cashUpRepo.save(cashUp));
        } else
            throw new cashUpNotFoundException(Properties.cashUpCreationNotPossible);
    }

    @Override
    @RosLogDebug
    public String deleteCashUp(UUID id) throws cashUpNotFoundException {
        Optional<CashUp> cashUpFromDB = cashUpRepo.findById(id);
        if (cashUpFromDB.get().getCashUpStatus() != CashUpStatus.PUBLISHED) {
            CashUp cashUp = cashUpFromDB.orElseThrow(() -> new cashUpNotFoundException(Properties.cashUpNotFound));
            cashUpRepo.delete(cashUp);
            return Properties.cashUpDeleted;
        } else
            throw new cashUpNotFoundException(Properties.cashUpDeleteException);
    }

    @Override
    @RosLogDebug
    public List<?> getCashUpSheets(CashUpStatus status, Date fromDate, Date toDate) throws cashUpNotFoundException {
        List<CashUp> cashUpFromDB = new ArrayList<>();
        List<CashUpDto> cashUpDtos = new ArrayList<>();
        List<BankingInfo> cashBankingInfos = new ArrayList<>();
        List<BankingInfoDto> bankingDtos = new ArrayList<>();
        List<Date> cashUpDates = new ArrayList<>();
        if (status.equals(CashUpStatus.DRAFT)) {
            cashUpFromDB = cashUpRepo.findCashUpSheets(status, fromDate, toDate);
            cashUpFromDB.forEach(data -> {
                cashUpDtos.add(restDtoMapper.convertToCashUpDto(data));
            });

            return cashUpDtos;
        } else if (status.equals(CashUpStatus.PUBLISHED)) {
            cashUpFromDB = cashUpRepo.findCashUpSheets(status, fromDate, toDate);
            cashUpDtos.addAll(checkPendingDeposits(cashUpFromDB, fromDate, toDate));
            return cashUpDtos;
        } else if (status.equals(CashUpStatus.BANKED)) {
            cashUpFromDB = cashUpRepo.findCashUpSheets(status, fromDate, toDate);
            cashBankingInfos.addAll(getBankingData(cashUpFromDB));
            cashBankingInfos.forEach(data -> {
                data.getCashUps().forEach(cashUp -> {
                    cashUpDates.add(cashUp.getCashUpDate());
                });
                bankingDtos.add(restDtoMapper.convertToBankingDto(data));
            });
            for (BankingInfoDto bankingInfoDto : bankingDtos) {
                bankingInfoDto.setCashUpDates(cashUpDates);
            }
            return bankingDtos;
        } else
            throw new cashUpNotFoundException(Properties.cashUpNotFound);
    }

    private List<BankingInfo> getBankingData(List<CashUp> cashUpFromDB) {
        List<BankingInfo> bankingInfo = new ArrayList<>();
        for (CashUp cashUp : cashUpFromDB) {
            bankingInfo.add(cashUp.getBankingInfo());
        }
        return bankingInfo;
    }

    private List<CashUpDto> checkPendingDeposits(List<CashUp> cashUpFromDB, Date fromDate, Date toDate) {
        float tillAmount = 0;
        List<CashUpDto> cashUpDtos = new ArrayList<>();
        for (CashUp data : cashUpFromDB) {
            for (TillSystem till : data.getCashnPdq().getTillSystems()) {
                tillAmount += till.getAmount();
                if (tillAmount != 0) {
                    cashUpDtos.add(restDtoMapper.convertToCashUpDto(data));
                } else
                    cashUpDtos.add(restDtoMapper.convertToCashUpDto(data));
            }
        }

        return cashUpDtos;

    }

    /*
     * Deposit services
     */
    @Override
    public BankingInfoDto CreateBanking(BankingInfoDto bankingDto) throws cashUpNotFoundException {

        BankingInfo bankingEntity = restDtoMapper.convertToBankingEntity(bankingDto);
        bankingEntity.addMetaData();
        BankingInfo bankingInfo = updateBankingInfo(bankingDto, bankingEntity);
        BankingInfoDto bankingInfoDto = restDtoMapper.convertToBankingDto(bankingInfoRepo.save(bankingInfo));
        bankingInfoDto = updateCashUpDatestoBankinginfo(bankingInfo, bankingInfoDto);
        return bankingInfoDto;
    }

    private BankingInfoDto updateCashUpDatestoBankinginfo(BankingInfo bankingInfo, BankingInfoDto bankingInfoDto) {
        List<Date> cashUpDates = new ArrayList<>();
        List<CashUpTimeIndicator> cashUpTimeIndicators = new ArrayList<>();
        int noOfcashUps = 0;
        for (CashUp date : bankingInfo.getCashUps()) {
            cashUpDates.add(date.getCashUpDate());
            cashUpTimeIndicators.add(date.getCashUpTimeIndicator());
            noOfcashUps++;
        }
        bankingInfoDto.setCashUpDates(cashUpDates);
        bankingInfoDto.setCashUpTimeIndicators(cashUpTimeIndicators);
        bankingInfoDto.setNoOfcashUps(noOfcashUps);

        return bankingInfoDto;

    }

    private BankingInfo updateBankingInfo(BankingInfoDto banking, BankingInfo bankingEntity)
            throws cashUpNotFoundException {
        List<CashUp> cashUps = new ArrayList<>();
        banking.getCashUpDates().forEach(date -> {
            cashUps.addAll(cashUpRepo.checkCashUpByDate(date));
        });
        if (cashUps.size() != 0) {
            for (CashUp cashUp : cashUps) {
                if (cashUp.getCashUpStatus() == CashUpStatus.DRAFT || cashUp.getCashUpStatus() == CashUpStatus.BANKED)
                    throw new cashUpNotFoundException(Properties.bankingNotCreated);
                else if (cashUp.getCashUpStatus() == CashUpStatus.PUBLISHED) {
                    cashUp.setCashUpStatus(CashUpStatus.BANKED);
                    cashUp.setBankingInfo(bankingEntity);
                }
            }
            bankingEntity.setCashUps(cashUps);
            return bankingEntity;
        } else
            throw new cashUpNotFoundException(Properties.bankingNotCreated);
    }

    @Override
    public String DeleteBankingById(UUID id) throws cashUpNotFoundException {
        Optional<BankingInfo> banking = bankingInfoRepo.findById(id);
        if (banking.isPresent()) {
            bankingInfoRepo.deleteById(id);
            return Properties.bankingDeleted;
        } else

            throw new cashUpNotFoundException(Properties.notFindTheRequestedBank);

    }

    @Override
    public BankingInfoDto getBankById(UUID id) throws cashUpNotFoundException {
        BankingInfo bankingInfo = bankingInfoRepo.getOne(id);
        if (!bankingInfo.equals(null)) {

            BankingInfoDto bankingInfoDto = restDtoMapper.convertToBankingDto(bankingInfo);
            bankingInfoDto = updateCashUpDatestoBankinginfo(bankingInfo, bankingInfoDto);

            return bankingInfoDto;

        } else
            throw new cashUpNotFoundException(Properties.bankingNotFound);
    }

    @Override
    @RosLogDebug
    public BankingInfoDto updateBanking(BankingInfoDto dto) throws cashUpNotFoundException {
        BankingInfo bankingInfo = bankingInfoRepo.getOne(dto.getId());
        if (bankingInfo != null) {

            restDtoMapper.updateBanking(dto, bankingInfo);
            BankingInfoDto bankingInfoDto = restDtoMapper.convertToBankingDto(bankingInfoRepo.save(bankingInfo));
            bankingInfoDto = updateCashUpDatestoBankinginfo(bankingInfo, bankingInfoDto);

            return bankingInfoDto;
        } else
            throw new cashUpNotFoundException(Properties.bankingNotCreated);
    }

    @Override
    @RosLogDebug
    public List<BankingInfoDto> getAllBanks() throws cashUpNotFoundException {
        List<BankingInfo> bankingInfos = bankingInfoRepo.findAll();
        List<BankingInfoDto> bankingInfoDtos = new ArrayList<>();
        BankingInfoDto bankingInfoDto;

        if (bankingInfos.size() != 0) {

            for (BankingInfo bankingInfo : bankingInfos) {
                bankingInfoDto = restDtoMapper.convertToBankingDto(bankingInfo);
                bankingInfoDto = updateCashUpDatestoBankinginfo(bankingInfo, bankingInfoDto);
                bankingInfoDtos.add(bankingInfoDto);
            }

            return bankingInfoDtos;
        } else
            throw new cashUpNotFoundException(Properties.bankingNotFound);

    }

    @Override
    public List<CashUpDto> getAllCashUpSheets() throws cashUpNotFoundException {
        List<CashUp> cashUps = cashUpRepo.findAll();
        List<CashUpDto> cashUpDtos = new ArrayList<>();
        if (cashUps.size() != 0) {
            cashUps.forEach(data -> {
                cashUpDtos.add(restDtoMapper.convertToCashUpDto(data));
            });
            return cashUpDtos;
        } else
            throw new cashUpNotFoundException(Properties.cashUpNotFound);
    }

}