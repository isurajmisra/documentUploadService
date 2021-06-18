package com.ros.accounting.cashup.model;

import com.ros.accounting.cashup.model.master.TillMaster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TillSystem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "till_system_id", length = 16)
    private UUID id;

    private float amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "till_id")
    private TillMaster tillMaster;
}
