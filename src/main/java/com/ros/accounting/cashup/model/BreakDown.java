package com.ros.accounting.cashup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreakDown implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "break_down_id", length = 16)
    private UUID id;

    private String name;

    private String code;

    private String billNumber;

    @Enumerated(value = EnumType.STRING)
    private BreakDownReason breakDownReason;

    private float amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private BreakDownDocument document;
}
