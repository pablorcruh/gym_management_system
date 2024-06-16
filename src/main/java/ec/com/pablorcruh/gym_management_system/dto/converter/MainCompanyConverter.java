package ec.com.pablorcruh.gym_management_system.dto.converter;

import ec.com.pablorcruh.gym_management_system.dto.request.MainCompanyDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.CampusEntity;
import ec.com.pablorcruh.gym_management_system.models.MainCompanyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class MainCompanyConverter {

    private final ModelMapper modelMapper;
    private final CampusConverter campusConverter;


    public MainCompanyConverter(ModelMapper modelMapper, CampusConverter campusConverter) {
        this.modelMapper = modelMapper;
        this.campusConverter = campusConverter;
    }

    public MainCompanyEntity toEntity(MainCompanyDTORequest request){
        return modelMapper.map(request, MainCompanyEntity.class);
    }

    public MainCompanyDTOResponse toResponse(MainCompanyEntity entity){
        Set<CampusEntity> campusesEntity = entity.getCampus();
        MainCompanyDTOResponse response = MainCompanyDTOResponse
                .builder()
                .ruc(entity.getRuc())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .id(entity.getId())
                .name(entity.getName())
                .active(entity.getActive())
                .campuses(campusesEntity.stream().map(c -> campusConverter.toResponse(c)).collect(Collectors.toList()))
                .build();
        return response;
    }
}
