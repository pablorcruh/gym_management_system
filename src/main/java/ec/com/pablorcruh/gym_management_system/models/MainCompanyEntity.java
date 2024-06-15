package ec.com.pablorcruh.gym_management_system.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "main_company")
@NoArgsConstructor
@Getter
@Setter
public class MainCompanyEntity extends BaseEntity{

    @Column
    private String name;

    @Column
    private String ruc;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "mainCompany")
    private Set<CampusEntity> campus = new HashSet<>();
}
