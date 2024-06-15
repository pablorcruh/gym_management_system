package ec.com.pablorcruh.gym_management_system.services.main_company;

import ec.com.pablorcruh.gym_management_system.dto.request.MainCompanyDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;

public interface MainCompanyService {

    MainCompanyDTOResponse createMainCompany(MainCompanyDTORequest request);
}
