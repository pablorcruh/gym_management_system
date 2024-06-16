package ec.com.pablorcruh.gym_management_system.services.main_company;

import ec.com.pablorcruh.gym_management_system.exceptions.NotFoundException;
import ec.com.pablorcruh.gym_management_system.dto.converter.MainCompanyConverter;
import ec.com.pablorcruh.gym_management_system.dto.request.MainCompanyDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.MainCompanyEntity;
import ec.com.pablorcruh.gym_management_system.repository.MainCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MainCompanyServiceImpl implements MainCompanyService{

    private final MainCompanyRepository repository;

    private final MainCompanyConverter converter;

    public MainCompanyServiceImpl(MainCompanyRepository repository, MainCompanyConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public MainCompanyDTOResponse createMainCompany(MainCompanyDTORequest request) {
        MainCompanyEntity entity = converter.toEntity(request);
        MainCompanyEntity savedEntity = repository.save(entity);
        MainCompanyDTOResponse response = converter.toResponse(savedEntity);
        return response;
    }

    @Override
    public void deleteMainCompany(UUID id) {
        MainCompanyEntity entity = findByIdEntity(id);
        if(entity == null){
            throw new NotFoundException("Main Company not Found");
        }
        entity.setActive(false);
        repository.save(entity);
    }

    @Override
    public MainCompanyDTOResponse updateMainCompany(UUID id, MainCompanyDTORequest request) {
        MainCompanyEntity entity = findByIdEntity(id);
        if(entity == null){
            throw new NotFoundException("Main Company not Found");
        }
        entity.setUpdatedAt(new Date());
        entity.setEmail(request.getEmail());
        entity.setName(request.getName());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setRuc(request.getRuc());
        repository.save(entity);
        MainCompanyDTOResponse response = converter.toResponse(entity);
        return response;
    }

    @Override
    public List<MainCompanyDTOResponse> getAllActive() {
        List<MainCompanyEntity> entities = repository.findAllMainCompanyActive();
        return entities.stream().map(m -> converter.toResponse(m)).collect(Collectors.toList());
    }

    @Override
    public MainCompanyDTOResponse findById(UUID id) {
        MainCompanyEntity entity = findByIdEntity(id);
        if(entity == null){
            throw new NotFoundException("Main Company not Found");
        }
        return converter.toResponse(entity);
    }


    private MainCompanyEntity findByIdEntity(UUID id){
        return repository.findById(id).orElse(null);
    }
}
