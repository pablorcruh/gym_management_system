package ec.com.pablorcruh.gym_management_system.util;

import ec.com.pablorcruh.gym_management_system.dto.request.PartnerDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.PartnerDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.PartnerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartnerCreationUtils {

    public static PartnerEntity createPartnerEntity(){
        PartnerEntity entity = new PartnerEntity();
        entity.setCampus(CreationUtils.createCampusEntity());
        entity.setEmail("pablo@mail.com");
        entity.setPhoneNumber("123456");
        entity.setCedula("11111111111");
        entity.setFirstName("pablo");
        entity.setFatherLastName("cruz");
        entity.setMotherLastName("herrera");
        entity.setId(UUID.fromString("df2729eb-bd1f-401a-8259-2094f4a9d4ac"));
        return entity;
    }

    public static PartnerDTORequest createPartnerDTORequest(){
        PartnerDTORequest request = PartnerDTORequest
                .builder()
                .cedula("123")
                .email("pablo@email")
                .fatherLastName("cruz")
                .motherLastName("herrera")
                .firstName("pablo")
                .build();
        return request;
    }

    public static List<PartnerEntity> createPartnerEntityList(){
        List<PartnerEntity> entities = new ArrayList<>();
        entities.add(createPartnerEntity());
        entities.add(createPartnerEntity());
        return entities;
    }
}
