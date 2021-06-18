package com.ros.accounting.cashup.model;

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
public class PettyCashInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "petty_cash_info_id", length = 16)
    private UUID id;

    private float amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "petty_cash_info_id")
    private List<PettyCashDocument> documents;

}
