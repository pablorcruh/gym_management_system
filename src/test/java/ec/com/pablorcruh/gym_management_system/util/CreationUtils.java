package ec.com.pablorcruh.gym_management_system.util;

import ec.com.pablorcruh.gym_management_system.dto.request.CampusDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.request.MainCompanyDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.CampusDTOResponse;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.CampusEntity;
import ec.com.pablorcruh.gym_management_system.models.MainCompanyEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreationUtils {

    public static MainCompanyEntity createMainCompanyEntity(){
        MainCompanyEntity entity = new MainCompanyEntity();
        entity.setRuc("123456789");
        entity.setName("main company");
        entity.setEmail("pablo@maincompany.com");
        entity.setPhoneNumber("123456765");
        entity.setId(UUID.fromString("e6616ae0-b732-4ce1-a256-1669a9664fb1"));
        return entity;
    }

    public static MainCompanyDTORequest createMainCompanyDTORequest(){
        MainCompanyDTORequest request = MainCompanyDTORequest
                .builder()
                .email("pablo@maincompany.com")
                .name("main company")
                .phoneNumber("123456765")
                .build();
        return request;
    }

    public static MainCompanyDTOResponse createMainCompanyDTOResponse(){
        MainCompanyDTOResponse response = MainCompanyDTOResponse
                .builder()
                .email("pablo@maincompany.com")
                .name("main company")
                .phoneNumber("123456765")
                .build();
        return response;
    }

    public static List<MainCompanyEntity> createMainCompanyEntityList(){
        List<MainCompanyEntity> entities = new ArrayList<>();
        entities.add(createMainCompanyEntity());
        entities.add(createMainCompanyEntity());
        return entities;
    }

    public static CampusEntity createCampusEntity(){
        CampusEntity entity = new CampusEntity();
        entity.setRuc("123456789");
        entity.setName("main company");
        entity.setEmail("pablo@maincompany.com");
        entity.setPhoneNumber("123456765");
        entity.setId(UUID.fromString("68ba3213-1c1c-440b-9cca-8594f536a1bb"));
        return entity;
    }

    public static CampusDTOResponse createCampusDTOResponse(){
        CampusDTOResponse response = CampusDTOResponse
                .builder()
                .email("pablo@maincompany.com")
                .name("main company")
                .phoneNumber("123456765")
                .build();
        return response;
    }

    public static CampusDTORequest createCampusDTORequest(){
        CampusDTORequest request = CampusDTORequest
                .builder()
                .email("pablo@maincompany.com")
                .name("main company")
                .phoneNumber("123456765")
                .build();
        return request;
    }

    public static List<CampusEntity> crateCampusEntityList(){
     List<CampusEntity> entities = new ArrayList<>();
     entities.add(createCampusEntity());
     entities.add(createCampusEntity());
     return entities;
    }
}
