package ec.com.pablorcruh.gym_management_system.repository;

import ec.com.pablorcruh.gym_management_system.models.MainCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MainCompanyRepository extends JpaRepository<MainCompanyEntity, UUID> {
}
