package ec.com.pablorcruh.gym_management_system.services.partner;

import ec.com.pablorcruh.gym_management_system.dto.request.PartnerDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.PartnerDTOResponse;

import java.util.List;
import java.util.UUID;

public interface PartnerService {

    PartnerDTOResponse createPartner(UUID idCampus, PartnerDTORequest request);

    void softDeletePartner(UUID idCampus, UUID idPartner);

    PartnerDTOResponse updatePartner(UUID idCampus, UUID idPartner, PartnerDTORequest request);

    List<PartnerDTOResponse> getAllActiveByCampusId(UUID idCampus);


}
