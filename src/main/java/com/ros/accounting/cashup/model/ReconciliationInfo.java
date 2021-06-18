package com.ros.accounting.cashup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author Ayush
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReconciliationInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "recon_info_id", length = 16)
    private UUID id;

    @Enumerated(value = EnumType.STRING)
    private ReconciliationType reconciliationType;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cash_recon_info_id")
    private List<CashReconciliationInfo> cashReconciliations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_recon_info_id")
    private List<CardReconciliationInfo> cardReconciliations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "thirdParty_recon_info_id")
    private List<ThirdPartyReconciliationInfo> thirdPartyReconciliations;

    private boolean isReconciled;
}
