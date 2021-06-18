package com.ros.accounting.cashup.model;

import com.ros.accounting.cashup.model.master.PettyCashMaster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PettyCash implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "petty_cash_id", length = 16)
    private UUID id;

    private float amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "petty_cash_master_id")
    private PettyCashMaster pettyCashMaster;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "petty_cash_id")
    private List<PettyCashDocument> documents;

}
