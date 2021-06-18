//package com.ros.accounting.reconciliation.model;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.PrimaryKeyJoinColumn;
//
//import com.ros.accounting.cashup.model.ThirdPartyInfo;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@PrimaryKeyJoinColumn(name = "reconciliation_id")
//public class ThirdPartyReconciliationInfo extends ReconciliationInfo{
//
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "third_party_info_id")
//	private List<ThirdPartyInfo> thirdPartyInfos;
//}
