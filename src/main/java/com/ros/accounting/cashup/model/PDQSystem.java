package com.ros.accounting.cashup.model;

import com.ros.accounting.cashup.model.master.PDQCardMaster;
import com.ros.accounting.cashup.model.master.PDQMachineMaster;
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
public class PDQSystem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pdq_system_id", length = 16)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pdq_machine_master_id")
    private PDQMachineMaster pdqMachineMaster;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pdq_card_master_id")
    private PDQCardMaster pdqCardMaster;

    private float amount;
}
