package ec.com.pablorcruh.gym_management_system.infrastructure.entities;

import ec.com.pablorcruh.gym_management_system.domain.enums.MembershipType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "membership")
public class MembershipEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private BigInteger price;

    @Column
    private LocalDateTime firstDay;

    @Column
    private LocalDateTime lastDay;

    @Column
    private MembershipType membershipType;

    @Column
    private Boolean isActive;
    @Column
    private String comments;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private PartnerEntity partner;

    @ManyToMany
    @JoinTable(
            name = "promotion-membership",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "membership_id")
    )
    private Set<PromotionEntity> promotions = new HashSet<>();
}
