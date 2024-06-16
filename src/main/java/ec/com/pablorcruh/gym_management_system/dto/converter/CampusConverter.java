package ec.com.pablorcruh.gym_management_system.dto.converter;

import ec.com.pablorcruh.gym_management_system.dto.request.CampusDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.CampusDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.CampusEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CampusConverter {

    private final ModelMapper modelMapper;
    public CampusConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CampusEntity toEntity(CampusDTORequest request){
        return modelMapper.map(request, CampusEntity.class);
    }

    public CampusDTOResponse toResponse(CampusEntity entity){
        CampusDTOResponse response = CampusDTOResponse
                .builder()
                .ruc(entity.getRuc())
                .name(entity.getName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .active(entity.getActive())
                .id(entity.getId())
                .build();
        return response;
    }
}
