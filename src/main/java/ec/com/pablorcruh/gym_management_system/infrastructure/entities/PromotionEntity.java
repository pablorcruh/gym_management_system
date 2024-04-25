package ec.com.pablorcruh.gym_management_system.infrastructure.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "promotion")
public class PromotionEntity extends BaseEntity{

    private String name;
    private int code;
    private LocalDateTime firstDay;
    private LocalDateTime lastDay;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private CampaignEntity campaign;

    @ManyToMany(mappedBy = "promotions")
    private Set<MembershipEntity> memberships = new HashSet<>();
}
