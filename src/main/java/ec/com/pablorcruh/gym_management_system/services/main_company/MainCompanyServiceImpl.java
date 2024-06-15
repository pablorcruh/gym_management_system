package ec.com.pablorcruh.gym_management_system.services.main_company;

import ec.com.pablorcruh.gym_management_system.dto.converter.MainCompanyConverter;
import ec.com.pablorcruh.gym_management_system.dto.request.MainCompanyDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.MainCompanyEntity;
import ec.com.pablorcruh.gym_management_system.repository.MainCompanyRepository;
import org.springframework.stereotype.Service;

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
}
