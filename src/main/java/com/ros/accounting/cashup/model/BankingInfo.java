package com.ros.accounting.cashup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankingInfo extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "banking_info_id")
    private UUID id;

    @Temporal(TemporalType.DATE)
    private Date bankingDate;

    @Enumerated(EnumType.STRING)
    private BankingTimeIndicator bankingTimeIndicator;

    @Column
    private int giroSlipNumber;

    @Column
    private float bankingTotal;

    @Column
    private float bankedTotal;

    private String reason;

    @Column(nullable = false)
    private String sealedBy;

    private long employeeId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bankingInfo")
    private List<CashUp> cashUps;


}
