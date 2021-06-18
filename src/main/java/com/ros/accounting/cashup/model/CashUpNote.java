package com.ros.accounting.cashup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "cashup_note")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CashUpNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cashup_Note_id")
    private UUID id;

    private String reason;

    private String reasonAddedBy;

    private String comments;

}
