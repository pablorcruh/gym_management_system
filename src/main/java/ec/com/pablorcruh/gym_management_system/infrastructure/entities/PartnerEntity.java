package ec.com.pablorcruh.gym_management_system.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "partner")
public class PartnerEntity extends PersonEntity{

    @OneToMany(mappedBy = "partner")
    private Set<MembershipEntity> memberships = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="campus_id")
    private CampusEntity campus;
}
