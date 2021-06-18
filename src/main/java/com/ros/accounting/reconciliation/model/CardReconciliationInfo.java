//package com.ros.accounting.reconciliation.model;
//
//import java.util.List;
//import java.util.UUID;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.PrimaryKeyJoinColumn;
//
//import com.ros.accounting.cashup.model.PDQSystem;
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
//public class CardReconciliationInfo extends ReconciliationInfo{
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "card_reconciliation_info")
//	private UUID id;
//	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "card_reconciliation_info")
//	private List<PDQSystem> pdqSystems;
//}
