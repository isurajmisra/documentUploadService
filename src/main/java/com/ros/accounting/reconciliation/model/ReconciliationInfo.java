//package com.ros.accounting.reconciliation.model;
//
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class ReconciliationInfo {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "reconciliation_info_id")
//	private UUID reconciliationInfoID;
//
//	private float variance;
//
//	private String note;
//
//	@Temporal(value = TemporalType.DATE)
//	private Date createdDate;
//	
//	@Enumerated(EnumType.STRING)
//	private MatchType matchType;
//
//}
