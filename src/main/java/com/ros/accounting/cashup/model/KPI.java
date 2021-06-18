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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class KPI implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kpi_id", length = 16)
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "kpi_id")
    private List<KPICover> kpiCovers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "kpi_id")
    private List<Complaint> complaints;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "kpi_id")
    private List<BreakDown> breakDownDetails;

}
