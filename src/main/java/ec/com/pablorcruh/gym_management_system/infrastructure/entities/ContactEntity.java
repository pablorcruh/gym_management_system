package ec.com.pablorcruh.gym_management_system.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact")
@NoArgsConstructor
public class ContactEntity extends BaseEntity{
}
