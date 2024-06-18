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

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public void softDeletePartner(UUID idCampus, UUID idPartner) {
        CampusEntity campusEntity = findByIdCampusEntity(idCampus);
        if(campusEntity == null){
            throw new NotFoundException(String.format("Campus with id: %s not found",idCampus));
        }
        PartnerEntity partnerEntity = findByIdPartnerEntity(idPartner);
        if(campusEntity == null){
            throw new NotFoundException(String.format("Partner with id: %s not found",idPartner));
        }
        partnerEntity.setActive(false);
        partnerEntity.setUpdatedAt(new Date());
        partnerRepository.save(partnerEntity);
    }

    @Override
    public PartnerDTOResponse updatePartner(UUID idCampus, UUID idPartner, PartnerDTORequest request) {
        CampusEntity campusEntity = findByIdCampusEntity(idCampus);
        if(campusEntity == null){
            throw new NotFoundException(String.format("Campus with id: %s not found",idCampus));
        }
        PartnerEntity partnerEntity = findByIdPartnerEntity(idPartner);
        if(campusEntity == null){
            throw new NotFoundException(String.format("Partner with id: %s not found",idPartner));
        }
        partnerEntity.setUpdatedAt(new Date());
        partnerEntity.setEmail(request.getEmail());
        partnerEntity.setCedula(request.getCedula());
        partnerEntity.setEmail(request.getCedula());
        partnerEntity.setFirstName(request.getFirstName());
        partnerEntity.setFatherLastName(request.getFatherLastName());
        partnerEntity.setMotherLastName(request.getMotherLastName());
        partnerEntity.setEmail(request.getEmail());
        PartnerEntity savedPartner = partnerRepository.save(partnerEntity);
        return partnerConverter.toResponse(savedPartner);
    }

    @Override
    public List<PartnerDTOResponse> getAllActiveByCampusId(UUID idCampus) {
        List<PartnerEntity> partners = partnerRepository.findAllActiveByCampusId(idCampus);
        return partners.stream().map(p -> partnerConverter.toResponse(p)).collect(Collectors.toList());
    }

    @Override
    public PartnerDTOResponse findPartnerById(UUID idCampus, UUID idPartner) {
        PartnerEntity response = partnerRepository.findByIdCampusAndIdPartner(idCampus, idPartner);
        return partnerConverter.toResponse(response);
    }

    private CampusEntity findByIdCampusEntity(UUID idCampus){
        return campusRepository.findById(idCampus).orElse(null);
    }

    private PartnerEntity findByIdPartnerEntity(UUID idPartner){
        return partnerRepository.findById(idPartner).orElse(null);
    }

}
