package com.ros.accounting.cashup.model;

import com.ros.accounting.cashup.model.master.ThirdPartyInfoMaster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ThirdParty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "third_party_id", length = 16)
    private UUID id;

    private float amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "third_party_id")
    private ThirdPartyInfoMaster thirdPartyInfoMaster;


//	@OneToOne(cascade = CascadeType.ALL)
//	@ElementCollection
//	@MapKeyColumn(name = "thrid_party_info")
//	@MapKeyEnumerated(EnumType.STRING)
//	@MapKey(name = "thrid_party_info")
//	@JoinTable(
//				name="thrid_party_info",
//			    joinColumns={@JoinColumn(name="fk_thirdpartInfo", referencedColumnName="third_party_info_id")},
//			    inverseJoinColumns={@JoinColumn(name="fk_thirdPartyInfoMaster", referencedColumnName="third_party_id")})
//	private Map<CashUpInfoMode, List<ThirdPartyInfo>> thirdPartyInfoes = new HashMap<CashUpInfoMode, List<ThirdPartyInfo>>();
}
