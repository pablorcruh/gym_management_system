package ec.com.pablorcruh.gym_management_system.repository;

import ec.com.pablorcruh.gym_management_system.models.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PartnerRepository extends JpaRepository<PartnerEntity, UUID> {

    @Query(value = "select p from PartnerEntity  p where p.campus.id =:idCampus and p.active = true")
    List<PartnerEntity> findAllActiveByCampusId(@Param(value = "idCampus") UUID idCampus);
}
