package ec.com.pablorcruh.gym_management_system.dto.converter;


import ec.com.pablorcruh.gym_management_system.dto.request.PartnerDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.PartnerDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.PartnerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PartnerConverter {
    private final ModelMapper modelMapper;

    public PartnerConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public PartnerEntity toEntity(PartnerDTORequest request){
        return modelMapper.map(request, PartnerEntity.class);
    }

    public PartnerDTOResponse toResponse(PartnerEntity entity){
        PartnerDTOResponse response = PartnerDTOResponse
                .builder()
                .id(entity.getId())
                .cedula(entity.getCedula())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .fatherLastName(entity.getFatherLastName())
                .motherLastName(entity.getMotherLastName())
                .firstName(entity.getFirstName())
                .build();
        return response;
    }
}
