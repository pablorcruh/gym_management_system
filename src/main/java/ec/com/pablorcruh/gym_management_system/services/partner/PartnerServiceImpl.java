package ec.com.pablorcruh.gym_management_system.services.partner;

import ec.com.pablorcruh.gym_management_system.dto.converter.PartnerConverter;
import ec.com.pablorcruh.gym_management_system.dto.request.PartnerDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.PartnerDTOResponse;
import ec.com.pablorcruh.gym_management_system.exceptions.NotFoundException;
import ec.com.pablorcruh.gym_management_system.models.CampusEntity;
import ec.com.pablorcruh.gym_management_system.models.PartnerEntity;
import ec.com.pablorcruh.gym_management_system.repository.CampusRepository;
import ec.com.pablorcruh.gym_management_system.repository.PartnerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PartnerServiceImpl implements PartnerService{
    private final PartnerRepository partnerRepository;
    private final PartnerConverter partnerConverter;
    private final CampusRepository campusRepository;
    public PartnerServiceImpl(PartnerRepository partnerRepository, PartnerConverter partnerConverter, CampusRepository campusRepository) {
        this.partnerRepository = partnerRepository;
        this.partnerConverter = partnerConverter;
        this.campusRepository = campusRepository;
    }

    @Override
    public PartnerDTOResponse createPartner(UUID idCampus, PartnerDTORequest request) {
        CampusEntity campusEntity = findByIdCampusEntity(idCampus);
        if(campusEntity == null){
            throw new NotFoundException(String.format("Campus with id: %s not found",idCampus));
        }
        PartnerEntity partnerEntity = partnerConverter.toEntity(request);
        partnerEntity.setCampus(campusEntity);
        PartnerEntity savedPartner = partnerRepository.save(partnerEntity);
        return partnerConverter.toResponse(savedPartner);
    }

    private CampusEntity findByIdCampusEntity(UUID idCampus){
        return campusRepository.findById(idCampus).orElse(null);
    }
}
