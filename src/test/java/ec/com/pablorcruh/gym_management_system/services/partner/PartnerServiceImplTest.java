package ec.com.pablorcruh.gym_management_system.services.partner;

import ec.com.pablorcruh.gym_management_system.dto.converter.CampusConverter;
import ec.com.pablorcruh.gym_management_system.dto.converter.PartnerConverter;
import ec.com.pablorcruh.gym_management_system.dto.response.CampusDTOResponse;
import ec.com.pablorcruh.gym_management_system.dto.response.PartnerDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.PartnerEntity;
import ec.com.pablorcruh.gym_management_system.repository.CampusRepository;
import ec.com.pablorcruh.gym_management_system.repository.PartnerRepository;
import ec.com.pablorcruh.gym_management_system.util.CreationUtils;
import ec.com.pablorcruh.gym_management_system.util.PartnerCreationUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartnerServiceImplTest {

    PartnerServiceImpl underTest;

    @Mock
    CampusRepository campusRepository;

    @Mock
    PartnerRepository partnerRepository;

    @Mock
    PartnerConverter partnerConverter;

    @Mock
    CampusConverter campusConverter;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setup(){
        modelMapper= new ModelMapper();
        partnerConverter = new PartnerConverter(modelMapper);
        campusConverter = new CampusConverter(modelMapper, partnerConverter);
        underTest = new PartnerServiceImpl(partnerRepository,partnerConverter,campusRepository);
    }

    @Test
    void shoulCreatePartner(){
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createCampusEntity()));
        when(partnerRepository.save(any(PartnerEntity.class))).thenReturn(PartnerCreationUtils.createPartnerEntity());
        PartnerDTOResponse response = underTest.createPartner(UUID.fromString("68ba3213-1c1c-440b-9cca-8594f536a1bb"), PartnerCreationUtils.createPartnerDTORequest());
        assertNotNull(response);
        assertThat(response.getFirstName().equals("pablo"));
        verify(campusRepository, times(1)).findById(any(UUID.class));
        verify(partnerRepository, times(1)).save(any(PartnerEntity.class));
    }

    @Test
    void shouldSoftDeletePartner(){
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createCampusEntity()));
        when(partnerRepository.findById(any(UUID.class))).thenReturn(Optional.of(PartnerCreationUtils.createPartnerEntity()));
        underTest.softDeletePartner(UUID.randomUUID(), UUID.randomUUID());
        verify(campusRepository,times(1)).findById(any(UUID.class));
        verify(partnerRepository,times(1)).save(any(PartnerEntity.class));
        verify(partnerRepository,times(1)).findById(any(UUID.class));
    }

    @Test
    void shouldupdatePartner(){
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createCampusEntity()));
        when(partnerRepository.findById(any(UUID.class))).thenReturn(Optional.of(PartnerCreationUtils.createPartnerEntity()));
        when(partnerRepository.save(any(PartnerEntity.class))).thenReturn(PartnerCreationUtils.createPartnerEntity());
        PartnerDTOResponse response = underTest.updatePartner(
                UUID.randomUUID(),
                UUID.randomUUID(),
                PartnerCreationUtils.createPartnerDTORequest());
        assertThat(response.getEmail().equals("pablo@mail.com"));
        assertNotNull(response);
        verify(partnerRepository,times(1)).save(any(PartnerEntity.class));
        verify(partnerRepository,times(1)).findById(any(UUID.class));
    }

    @Test
    void shouldGetAllActivePartnerByCampus(){
        when(partnerRepository.findAllActiveByCampusId(any(UUID.class))).thenReturn(PartnerCreationUtils.createPartnerEntityList());
        Page<PartnerDTOResponse> response = underTest.getAllActiveByCampusId(UUID.fromString("68ba3213-1c1c-440b-9cca-8594f536a1bb"), 0, 10);
        assertNotNull(response);
        assertEquals(response.getTotalElements(),2);
        verify(partnerRepository, times(1)).findAllActiveByCampusId(UUID.fromString("68ba3213-1c1c-440b-9cca-8594f536a1bb"));
    }

    @Test
    void shouldGetOnePartner(){
        when(partnerRepository.findByIdCampusAndIdPartner(any(UUID.class), any(UUID.class))).thenReturn(PartnerCreationUtils.createPartnerEntity());
        PartnerDTOResponse response = underTest.findPartnerById(UUID.fromString("68ba3213-1c1c-440b-9cca-8594f536a1bb"),UUID.fromString("df2729eb-bd1f-401a-8259-2094f4a9d4ac"));
        assertNotNull(response);
        Assertions.assertThat(response.getEmail().equals("pablo@maincompany.com"));
        verify(partnerRepository, times(1)).findByIdCampusAndIdPartner(any(UUID.class),any(UUID.class));
    }
}