package ec.com.pablorcruh.gym_management_system.services.campus;

import ec.com.pablorcruh.gym_management_system.dto.request.CampusDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.CampusDTOResponse;
import ec.com.pablorcruh.gym_management_system.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface CampusService {

    CampusDTOResponse createCampus(UUID mainCompanyId, CampusDTORequest request);

    void softDeleteCampus(UUID mainCompanyId, UUID campusId);

    CampusDTOResponse updateCampus(UUID mainCompanyId, UUID campusId, CampusDTORequest request);

    List<CampusDTOResponse> getAllCampusActive(UUID idMainCompany);

    CampusDTOResponse findById(UUID mainCompanyId, UUID campusId);
}
