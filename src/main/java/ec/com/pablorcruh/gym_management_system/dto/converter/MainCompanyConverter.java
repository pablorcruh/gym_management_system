package ec.com.pablorcruh.gym_management_system.dto.converter;

import ec.com.pablorcruh.gym_management_system.dto.request.MainCompanyDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.MainCompanyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainCompanyConverter {

    private final ModelMapper modelMapper;

    public MainCompanyConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MainCompanyEntity toEntity(MainCompanyDTORequest request){
        return modelMapper.map(request, MainCompanyEntity.class);
    }

    public MainCompanyDTOResponse toResponse(MainCompanyEntity entity){
        MainCompanyDTOResponse response = MainCompanyDTOResponse
                .builder()
                .ruc(entity.getRuc())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .id(entity.getId())
                .build();
        return response;
    }
}
