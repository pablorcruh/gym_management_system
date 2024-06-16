package ec.com.pablorcruh.gym_management_system.repository;

import ec.com.pablorcruh.gym_management_system.models.MainCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MainCompanyRepository extends JpaRepository<MainCompanyEntity, UUID> {

    @Query(value = "select m from MainCompanyEntity m where m.active = true")
    List<MainCompanyEntity> findAllMainCompanyActive();
}
