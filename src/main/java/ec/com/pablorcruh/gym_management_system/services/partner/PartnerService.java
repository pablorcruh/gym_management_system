package ec.com.pablorcruh.gym_management_system.services.partner;

import ec.com.pablorcruh.gym_management_system.dto.request.PartnerDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.PartnerDTOResponse;

import java.util.UUID;

public interface PartnerService {

    PartnerDTOResponse createPartner(UUID idCampus, PartnerDTORequest request);

}
