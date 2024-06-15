package ec.com.pablorcruh.gym_management_system.util;

import ec.com.pablorcruh.gym_management_system.dto.request.MainCompanyDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.MainCompanyEntity;

public class CreationUtils {

    public static MainCompanyEntity createMainCompanyEntity(){
        MainCompanyEntity entity = new MainCompanyEntity();
        return entity;
    }

    public static MainCompanyDTORequest createMainCompanyDTOResponse(){
        MainCompanyDTORequest request = MainCompanyDTORequest.builder().build();
        return request;
    }
}
