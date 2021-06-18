/**
 *
 */
package com.ros.accounting.cashup.service.impl;

import com.ros.accounting.cashup.controller.dto.*;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.mapper.RestDtoMapper;
import com.ros.accounting.cashup.model.*;
import com.ros.accounting.cashup.repository.BankingInfoRepository;
import com.ros.accounting.cashup.repository.CashUpRepository;
import com.ros.accounting.cashup.service.ReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ayush
 *
 */


@Service
public class ReconciliationServiceImpl implements ReconciliationService {


    @Autowired
    BankingInfoRepository bankingRepo;
    @Autowired
    private RestDtoMapper restDtoMapper;
    @Autowired
    private CashUpRepository cashUpRepo;

    public static String cardContainsDate(List<CardReconciliationLogDto> list, Date id) {
        int i = 0;
        for (CardReconciliationLogDto object : list) {
            if (object.getDate().equals(id)) {
                return object.getCardName() + "_" + String.valueOf(i);
            }
            i = i + 1;
        }
        return "_";
    }

    public static String thirdPartyContainsDate(List<ThirdPartyReconciliationLogDto> list, Date id) {
        int i = 0;
        for (ThirdPartyReconciliationLogDto object : list) {
            if (object.getCashUpDate().equals(id)) {
                return object.getThirdPartyName() + "_" + String.valueOf(i);
            }
            i = i + 1;
        }
        return "_";
    }

    public static String cashContainsDate(List<CashReconciliationLogDto> list, Date id) {
        int i = 0;
        for (CashReconciliationLogDto object : list) {
            if (object.getDate().equals(id)) {
                return String.valueOf(object.getBankedTotal()) + "_" + String.valueOf(i);
            }
            i = i + 1;
        }
        return "_";
    }

    @Override
    public List<ReconciliationLogDto> reconcileCashUps(List<ReconciliationLogDto> reconciliationLogs)
            throws cashUpNotFoundException {


        return null;
    }

    @Override
    public ReconciliationLogDto reconcileCashUp(ReconciliationLogDto reconciliationLog) throws cashUpNotFoundException {


        return null;
    }

    @Override
    public List<ReconciliationLogDto> getCashUps(CashUpStatus status, Date fromDate, Date toDate)
            throws cashUpNotFoundException {


        return null;
    }

    @Override
    public ReconciliationInfoDto getPendingReconciliationSummary(Date fromDate, Date toDate) throws cashUpNotFoundException {
        List<CashUp> cashUps = new ArrayList<>();
        ReconciliationInfoDto reconciliationInfoDto = new ReconciliationInfoDto();
        float cashtotal = 0;
        float cardtotal = 0;
        float thirdpartytotal = 0;
        cashUps = cashUpRepo.findReconciliationSheets(fromDate, toDate);
        int pendingCash = 0;
        int pendingCard = 0;
        int pendingThirdParty = 0;
        for (CashUp cashUp : cashUps) {

            if (!cashUp.getCashUpStatus().equals(CashUpStatus.RECONCILED) && cashUp.getCashUpStatus().equals(CashUpStatus.BANKED)) {
                pendingCash++;
                System.out.println(cashUp.getCashUpStatus());
                for (TillSystem tillSystemDto : cashUp.getCashnPdq().getTillSystems()) {
                    cashtotal += tillSystemDto.getAmount();

                }
            }
            if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED) || cashUp.getCashUpStatus().equals(CashUpStatus.BANKED)) {
                pendingCard++;
                pendingThirdParty++;
                System.out.println(cashUp.getCashUpStatus());
                for (PDQSystem pdqSystem : cashUp.getCashnPdq().getPdqSystems()) {
                    cardtotal += pdqSystem.getAmount();
                }
                for (ThirdPartyInfo thirdParty : cashUp.getThirdPartyInfo()) {
                    thirdpartytotal += thirdParty.getAmount();
                }

            }
        }

        reconciliationInfoDto.setCashtotals(cashtotal);
        reconciliationInfoDto.setCardtotals(cardtotal);
        reconciliationInfoDto.setThirdpartytotals(thirdpartytotal);
        reconciliationInfoDto.setPendingCash(pendingCash);
        reconciliationInfoDto.setPendingCard(pendingCard);
        reconciliationInfoDto.setPendingThirdParty(pendingThirdParty);
        return reconciliationInfoDto;
    }


    @Override
    public ReconciliationInfoDto getReconciledSummary(Date fromDate, Date toDate) throws cashUpNotFoundException {
        List<CashUp> cashUps = new ArrayList<>();
        ReconciliationInfoDto reconciliationInfoDto = new ReconciliationInfoDto();
        float reconciledTotal = 0;
        float cashtotal = 0;
        float cardtotal = 0;
        float thirdpartytotal = 0;
        cashUps = cashUpRepo.findReconciliationSheets(fromDate, toDate);

        for (CashUp cashUp : cashUps) {

            if (cashUp.getCashUpStatus().equals(CashUpStatus.RECONCILED)) {

                System.out.println(cashUp.getCashUpStatus());
                for (TillSystem tillSystemDto : cashUp.getCashnPdq().getTillSystems()) {
                    cashtotal += tillSystemDto.getAmount();

                }
                for (PDQSystem pdqSystem : cashUp.getCashnPdq().getPdqSystems()) {
                    cardtotal += pdqSystem.getAmount();
                }
                for (ThirdPartyInfo thirdParty : cashUp.getThirdPartyInfo()) {
                    thirdpartytotal += thirdParty.getAmount();
                }
                reconciledTotal = cashtotal + cardtotal + thirdpartytotal;
            }
        }

        reconciliationInfoDto.setCashtotals(cashtotal);
        reconciliationInfoDto.setCardtotals(cardtotal);
        reconciliationInfoDto.setThirdpartytotals(thirdpartytotal);
        reconciliationInfoDto.setReconciledTotal(reconciledTotal);

        return reconciliationInfoDto;
    }


    @Override
    public ReconciliationLogDto getReconciliationLog(Date fromDate, Date toDate, String reconType) throws cashUpNotFoundException {
        List<CashUp> cashUps = new ArrayList<>();
        List<BankingInfo> bankInfo = new ArrayList<>();
        List<CashReconciliationLogDto> cashreconDto = new ArrayList<>();
        List<CardReconciliationLogDto> cardreconDto = new ArrayList<>();
        List<ThirdPartyReconciliationLogDto> thirdPartyreconDto = new ArrayList<>();
        ReconciliationLogDto reconciliationLogDto = new ReconciliationLogDto();


        if (reconType.equals("card") || reconType.equals("thirdparty")) {
            cashUps = cashUpRepo.findReconciliationSheets(fromDate, toDate);
            for (CashUp cashUp : cashUps) {
                if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED) || cashUp.getCashUpStatus().equals(CashUpStatus.RECONCILED)) {

                    if (reconType.equals("card")) {
                        for (PDQSystem pdqSystem : cashUp.getCashnPdq().getPdqSystems()) {
                            if (pdqSystem.getAmount() > 0) {
                                CardReconciliationLogDto cardItem = new CardReconciliationLogDto();
                                cardItem.setDate(cashUp.getCashUpDate());
                                if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED)) {
                                    cardItem.setSheetStatus("PENDING");
                                } else {
                                    cardItem.setSheetStatus("RECONCILED");
                                }
                                String cardContainsInfo = cardContainsDate(cardreconDto, cashUp.getCashUpDate());

                                if (cardContainsInfo != "_") {
                                    String name = cardContainsInfo.split("_")[0];
                                    int nameIndex = Integer.valueOf(cardContainsInfo.split("_")[1]);
                                    name += "," + pdqSystem.getPdqCardMaster().getName();
                                    cardreconDto.remove(nameIndex);
                                    cardItem.setCardName(name);

                                } else {
                                    cardItem.setCardName(pdqSystem.getPdqCardMaster().getName());
                                }
                                cardreconDto.add(cardItem);
                            }
                        }
                    } else if (reconType.equals("thirdparty")) {
                        for (ThirdPartyInfo thirdParty : cashUp.getThirdPartyInfo()) {
                            if (thirdParty.getAmount() > 0) {
                                ThirdPartyReconciliationLogDto thirdPartyItem = new ThirdPartyReconciliationLogDto();

                                thirdPartyItem.setCashUpDate(cashUp.getCashUpDate());
                                if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED)) {
                                    thirdPartyItem.setSheetStatus("PENDING");
                                } else {
                                    thirdPartyItem.setSheetStatus("RECONCILED");
                                }

                                String thirdPartyContainsInfo = thirdPartyContainsDate(thirdPartyreconDto, cashUp.getCashUpDate());

                                if (thirdPartyContainsInfo != "_") {
                                    String name = thirdPartyContainsInfo.split("_")[0];
                                    int nameIndex = Integer.valueOf(thirdPartyContainsInfo.split("_")[1]);
                                    name += "," + thirdParty.getThirdPartyInfoMaster().getName();
                                    thirdPartyreconDto.remove(nameIndex);
                                    thirdPartyItem.setThirdPartyName(name);

                                } else {
                                    thirdPartyItem.setThirdPartyName(thirdParty.getThirdPartyInfoMaster().getName());
                                }

                                thirdPartyreconDto.add(thirdPartyItem);
                            }
                        }
                    }
                }

            }
            reconciliationLogDto.setCardreconDto(cardreconDto);
            reconciliationLogDto.setThirdPartyreconDto(thirdPartyreconDto);


        } else if (reconType.equals("cash")) {
            bankInfo = bankingRepo.findReconciliationSheets(fromDate, toDate);
            for (BankingInfo baInfo : bankInfo) {
                CashReconciliationLogDto cashItem = new CashReconciliationLogDto();
                cashItem.setDate(baInfo.getBankingDate());

                String cashContainsInfo = cashContainsDate(cashreconDto, baInfo.getBankingDate());

                if (cashContainsInfo != "_") {
                    float value = Float.valueOf(cashContainsInfo.split("_")[0]);
                    int nameIndex = Integer.valueOf(cashContainsInfo.split("_")[1]);
                    value += baInfo.getBankedTotal();
                    cashreconDto.remove(nameIndex);
                    cashItem.setBankedTotal(value);

                } else {
                    cashItem.setBankedTotal(baInfo.getBankedTotal());
                }


                cashreconDto.add(cashItem);
            }
            reconciliationLogDto.setCashreconDto(cashreconDto);
        }
        return reconciliationLogDto;

    }


}
