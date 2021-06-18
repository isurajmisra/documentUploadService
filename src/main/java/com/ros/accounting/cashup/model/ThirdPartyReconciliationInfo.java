/**
 *
 */
package com.ros.accounting.cashup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Ayush
 *
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyReconciliationInfo extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "thirdParty_recon_info_id", length = 16)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pdq_system_id")
    private ThirdPartyInfo thidPartyManual;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "pdq_system_id")
//	private ThirdPartyInfo thidPartyAPI;

    @Enumerated(value = EnumType.STRING)
    private ReconciliationMatchType reconciliationMatchType;

    private float thirdPartyManualAmount;

    private float thirdPartyAPIAmount;

}
