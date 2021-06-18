package com.ros.accounting.cashup.mapper;

import com.ros.accounting.cashup.controller.dto.*;
import com.ros.accounting.cashup.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the converting entities and dtos
 */
@Mapper
@Component
public interface RestDtoMapper {

    RestDtoMapper mapper = Mappers.getMapper(RestDtoMapper.class);

    @Mapping(target = "KPITotal", source = "KPITotal")
    @Mapping(target = "Delivery", source = "deliverytotal")
    @Mapping(target = "PDQ", source = "PDQtotal")
    @Mapping(target = "CASH", source = "CASHtotal")
    @Mapping(target = "EPOS", source = "EPOStotal")
    @Mapping(target = "note.reason", source = "reason")
    @Mapping(target = "note.reasonAddedBy", source = "reasonAddedBy")
    CashUp convertToCashUpEntity(CashUpDto dto);

    @Mapping(target = "KPITotal", source = "KPITotal")
    @Mapping(target = "deliverytotal", source = "delivery")
    @Mapping(target = "PDQtotal", source = "PDQ")
    @Mapping(target = "CASHtotal", source = "CASH")
    @Mapping(target = "EPOStotal", source = "EPOS")
    @Mapping(source = "note.reason", target = "reason")
    @Mapping(source = "note.reasonAddedBy", target = "reasonAddedBy")
    CashUpDto convertToCashUpDto(CashUp entity);

    CashUpSheetDto convertToCashUpSheetDto(CashUp entity);

    @Mapping(source = "note.reason", target = "reason")
    @Mapping(source = "note.reasonAddedBy", target = "reasonAddedBy")
    void updateCashUp(CashUp entity, @MappingTarget CashUpDto dto);

    @Mapping(target = "KPITotal", source = "KPITotal")
    @Mapping(target = "delivery", source = "deliverytotal")
    @Mapping(target = "PDQ", source = "PDQtotal")
    @Mapping(target = "CASH", source = "CASHtotal")
    @Mapping(target = "EPOS", source = "EPOStotal")
    void updateCashUp(CashUpDto dto, @MappingTarget CashUp cashUp);

    @Mapping(source = "pettyCashName", target = "pettyCashMaster.name")
    PettyCash convertToPettyCashEntity(PettyCashDto dto);

    @Mapping(source = "name", target = "tillMaster.name")
    TillSystem convertToTillCashEntity(TillSystemDto dto);

    @Mapping(source = "name", target = "pdqMachineMaster.name")
    @Mapping(source = "cardName", target = "pdqCardMaster.name")
    PDQSystem convertToPdqSystem(PDQSystemDto dto);

    @Mapping(source = "name", target = "thirdPartyInfoMaster.name")
    ThirdPartyInfo convertToThirdPartyInfo(ThirdPartyInfoDto dto);

    @Mapping(target = "pettyCashName", source = "pettyCashMaster.name")
    PettyCashDto convertToPettyCashDto(PettyCash entity);

    @Mapping(target = "name", source = "tillMaster.name")
    TillSystemDto convertToTillSystemDto(TillSystem entity);

    @Mapping(target = "name", source = "pdqMachineMaster.name")
    @Mapping(target = "cardName", source = "pdqCardMaster.name")
    PDQSystemDto convertToPdqSystemDto(PDQSystem entity);

    @Mapping(target = "name", source = "thirdPartyInfoMaster.name")
    ThirdPartyInfoDto convertToThirdPartyInfoDto(ThirdPartyInfo entity);

    BankingInfo convertToBankingEntity(BankingInfoDto dto);

    void updateBanking(BankingInfoDto bankingInfoDto, @MappingTarget BankingInfo bankingInfo);

    List<BankingInfoDto> convertToBankingDto(List<BankingInfo> list);

    //	@Mapping(source = "cashUpDates" , target = "cashUps.cashUpDate")
    BankingInfoDto convertToBankingDto(BankingInfo bankingInfo);

    BankingInfoDto convertToBankingDto(Optional<BankingInfo> bankingInfo);

    CashUpDto convertToCashUpDto(Optional<CashUp> cashUp);


    void getBanking(List<BankingInfoDto> bankingInfoDto, @MappingTarget List<BankingInfo> bankingInfo);

}
