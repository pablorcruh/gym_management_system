package ec.com.pablorcruh.gym_management_system.infrastructure.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "campus")
public class CampusEntity extends BaseEntity{
    @Column
    private String name;
    @Column
    private String ruc;

    @OneToMany(mappedBy = "campus")
    private Set<PartnerEntity> partners= new HashSet<>();

    @OneToMany(mappedBy = "campus")
    private Set<CampaignEntity> campaigns = new HashSet<>();

}
