package com.ros.accounting.cashup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PettyCashDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pettycash_document_id", length = 16)
    private UUID id;

    private String url;

}
