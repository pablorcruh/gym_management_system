package ec.com.pablorcruh.gym_management_system.repository;

import ec.com.pablorcruh.gym_management_system.models.CampusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CampusRepository extends JpaRepository<CampusEntity, UUID> {

    @Query(value = "select c from CampusEntity c where c.mainCompany.id = :idMainCompany and c.active=true")
    List<CampusEntity> findAllActive(@Param(value = "idMainCompany")UUID idMainCompany);
}
