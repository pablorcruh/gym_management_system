package ec.com.pablorcruh.gym_management_system.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonEntity extends BaseEntity{

    @Column
    private String name;

    @Column
    private String fatherLastName;

    @Column
    private String motherLastName;
    @Column
    private String cedula;
}
