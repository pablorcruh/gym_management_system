package ec.com.pablorcruh.gym_management_system.services.main_company;

import ec.com.pablorcruh.gym_management_system.dto.request.MainCompanyDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;

import java.util.List;
import java.util.UUID;

public interface MainCompanyService {

    MainCompanyDTOResponse createMainCompany(MainCompanyDTORequest request);

    void deleteMainCompany(UUID id);

    MainCompanyDTOResponse updateMainCompany(UUID id, MainCompanyDTORequest request);

    List<MainCompanyDTOResponse> getAllActive();

    MainCompanyDTOResponse findById(UUID id);
}
