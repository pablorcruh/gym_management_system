package ec.com.pablorcruh.gym_management_system.dto.converter;

import ec.com.pablorcruh.gym_management_system.dto.request.CampusDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.CampusDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.CampusEntity;
import ec.com.pablorcruh.gym_management_system.models.PartnerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class CampusConverter {

    private final ModelMapper modelMapper;

    private final PartnerConverter partnerConverter;

    public CampusConverter(ModelMapper modelMapper, PartnerConverter partnerConverter) {
        this.modelMapper = modelMapper;
        this.partnerConverter = partnerConverter;
    }

    public CampusEntity toEntity(CampusDTORequest request){
        return modelMapper.map(request, CampusEntity.class);
    }

    public CampusDTOResponse toResponse(CampusEntity entity){
        Set<PartnerEntity> partnersEntity = entity.getPartners();
        CampusDTOResponse response = CampusDTOResponse
                .builder()
                .ruc(entity.getRuc())
                .name(entity.getName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .active(entity.getActive())
                .id(entity.getId())
                .partners(partnersEntity.stream().map(p -> partnerConverter.toResponse(p)).collect(Collectors.toList()))
                .build();
        return response;
    }
}
