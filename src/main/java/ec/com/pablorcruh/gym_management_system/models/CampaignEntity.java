package ec.com.pablorcruh.gym_management_system.models;

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
@Table(name = "campaign")
public class CampaignEntity extends BaseEntity{
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="campus_id")
    private CampusEntity campus;

    @OneToMany(mappedBy = "campaign")
    private Set<PromotionEntity> promotions = new HashSet<>();
}
