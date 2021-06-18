package com.ros.accounting.cashup.model;

import com.ros.accounting.cashup.model.master.ThirdPartyInfoMaster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "thrid_party_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ThirdPartyInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "third_party_info_id", length = 16)
    private UUID id;

    private float amount;

    @Enumerated(EnumType.STRING)
    private CashUpInfoMode cashUpInfoMode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "third_party_id")
    private ThirdPartyInfoMaster thirdPartyInfoMaster;
}
