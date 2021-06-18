///**
// * 
// */
//package com.ros.accounting.reconciliation.model;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import com.ros.accounting.cashup.model.BaseEntity;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
///**
// * @author Ayush
// *
// */
//
//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
//
//public class ReconciliationLog implements Serializable {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "reconciliation_id")
//	private UUID reconciliationID;
//	
//	@Temporal(value = TemporalType.DATE)
//	private Date reconciliationDate;
//	
//	@Temporal(value = TemporalType.DATE)
//	private Date LastUpdatedDate;
//	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "reconciliation_id")
//	private List<ReconciliationInfo> reconciliationInfo;
//
//}
