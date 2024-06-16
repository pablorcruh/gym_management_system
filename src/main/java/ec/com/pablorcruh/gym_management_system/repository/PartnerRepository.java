package ec.com.pablorcruh.gym_management_system.repository;

import ec.com.pablorcruh.gym_management_system.models.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartnerRepository extends JpaRepository<PartnerEntity, UUID> {

}
