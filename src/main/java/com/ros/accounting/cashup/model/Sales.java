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
 * @author Mohith This class represents the main entity for tax and money
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Sales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sales_id", length = 16)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private CashUpInfoMode cashUpInfoMode;

    private float foodPayment;

    private float drinksPayment;

    private float takeAwayPayment;

    private float otherPayment;

    private float serviceCharges;

    private float creditCardTip;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sales_id")
    private List<TaxInfo> taxInfo;

}
