package ec.com.pablorcruh.gym_management_system.services.campus;

import ec.com.pablorcruh.gym_management_system.dto.converter.CampusConverter;
import ec.com.pablorcruh.gym_management_system.dto.request.CampusDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.CampusDTOResponse;
import ec.com.pablorcruh.gym_management_system.exceptions.NotFoundException;
import ec.com.pablorcruh.gym_management_system.models.CampusEntity;
import ec.com.pablorcruh.gym_management_system.models.MainCompanyEntity;
import ec.com.pablorcruh.gym_management_system.repository.CampusRepository;
import ec.com.pablorcruh.gym_management_system.repository.MainCompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CampusServiceImpl implements CampusService{

    private final CampusRepository campusRepository;

    private final MainCompanyRepository mainCompanyRepository;

    private final CampusConverter converter;

    public CampusServiceImpl(CampusRepository campusRepository, MainCompanyRepository mainCompanyRepository, CampusConverter converter) {
        this.campusRepository = campusRepository;
        this.mainCompanyRepository = mainCompanyRepository;
        this.converter = converter;
    }

    @Override
    public CampusDTOResponse createCampus(UUID mainCompanyId, CampusDTORequest request){
        MainCompanyEntity mainCompanyEntity = findMainCompanyEntityById(mainCompanyId);
        if(mainCompanyEntity == null){
            throw new NotFoundException(String.format("Main company with id %s no found", mainCompanyId));
        }
        CampusEntity entity = converter.toEntity(request);
        entity.setMainCompany(mainCompanyEntity);
        CampusEntity savedEntity = campusRepository.save(entity);
        return converter.toResponse(savedEntity);
    }

    @Override
    public void softDeleteCampus(UUID mainCompanyId, UUID campusId) {
        MainCompanyEntity mainCompanyEntity = findMainCompanyEntityById(mainCompanyId);
        if(mainCompanyEntity == null){
            throw new NotFoundException(String.format("Main company with id %s not found", mainCompanyId));
        }
        CampusEntity campusEntity = findCampusEntityById(campusId);
        if(campusEntity == null){
            throw new NotFoundException(String.format("Campus with id %s not found", campusId));
        }
        campusEntity.setActive(false);
        campusEntity.setUpdatedAt(new Date());
        campusRepository.save(campusEntity);
    }

    @Override
    public CampusDTOResponse updateCampus(UUID mainCompanyId, UUID campusId, CampusDTORequest request) {
        MainCompanyEntity mainCompanyEntity = findMainCompanyEntityById(mainCompanyId);
        if(mainCompanyEntity == null){
            throw new NotFoundException(String.format("Main company with id %s not found", mainCompanyId));
        }
        CampusEntity campusEntity = findCampusEntityById(campusId);
        if(campusEntity == null){
            throw new NotFoundException(String.format("Campus with id %s not found", campusId));
        }
        campusEntity.setEmail(request.getEmail());
        campusEntity.setRuc(request.getRuc());
        campusEntity.setName(request.getName());
        campusEntity.setPhoneNumber(request.getPhoneNumber());
        campusEntity.setUpdatedAt(new Date());
        CampusEntity savedEntity = campusRepository.save(campusEntity);
        return converter.toResponse(savedEntity);
    }

    @Override
    public Page<CampusDTOResponse> getAllCampusActive(UUID idMainCompany, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        MainCompanyEntity mainCompanyEntity = findMainCompanyEntityById(idMainCompany);
        if(mainCompanyEntity == null){
            throw new NotFoundException(String.format("Main company with id %s not found", idMainCompany));
        }
        List<CampusEntity> entities = campusRepository.findAllActive(idMainCompany);
        Page<CampusEntity> campusPage = new PageImpl<>(entities, pageable, entities.size());
        return campusPage.map(p -> converter.toResponse(p));
    }

    @Override
    public CampusDTOResponse findById(UUID mainCompanyId, UUID campusId) {
        MainCompanyEntity mainCompanyEntity = findMainCompanyEntityById(mainCompanyId);
        if(mainCompanyEntity == null){
            throw new NotFoundException(String.format("Main company with id %s not found", mainCompanyId));
        }
        CampusEntity campusEntity = findCampusEntityById(campusId);
        if(campusEntity == null){
            throw new NotFoundException(String.format("Campus with id %s not found", campusId));
        }
        return converter.toResponse(campusEntity);
    }

    private MainCompanyEntity findMainCompanyEntityById(UUID mainCompanyId){
        return mainCompanyRepository.findById(mainCompanyId).orElse(null);
    }

    private CampusEntity findCampusEntityById(UUID campusId){
        return campusRepository.findById(campusId).orElse(null);
    }


}
