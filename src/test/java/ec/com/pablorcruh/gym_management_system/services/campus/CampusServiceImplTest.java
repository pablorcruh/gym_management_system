package ec.com.pablorcruh.gym_management_system.services.campus;

import ec.com.pablorcruh.gym_management_system.dto.converter.CampusConverter;
import ec.com.pablorcruh.gym_management_system.dto.response.CampusDTOResponse;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;
import ec.com.pablorcruh.gym_management_system.exceptions.NotFoundException;
import ec.com.pablorcruh.gym_management_system.models.CampusEntity;
import ec.com.pablorcruh.gym_management_system.repository.CampusRepository;
import ec.com.pablorcruh.gym_management_system.repository.MainCompanyRepository;
import ec.com.pablorcruh.gym_management_system.util.CreationUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampusServiceImplTest {

    CampusServiceImpl underTest;

    @Mock
    CampusRepository campusRepository;

    @Mock
    MainCompanyRepository mainCompanyRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    CampusConverter campusConverter;

    @BeforeEach
    void setup(){
        modelMapper = new ModelMapper();
        campusConverter = new CampusConverter(modelMapper);
        underTest = new CampusServiceImpl(campusRepository, mainCompanyRepository, campusConverter);
    }

    @Test
    void shouldCreateCampus(){
        when(mainCompanyRepository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createMainCompanyEntity()));
        when(campusRepository.save(any(CampusEntity.class))).thenReturn(CreationUtils.createCampusEntity());
        CampusDTOResponse response = underTest.createCampus(UUID.randomUUID(),CreationUtils.createCampusDTORequest());
        assertNotNull(response);
        verify(mainCompanyRepository, times(1)).findById(any(UUID.class));
        verify(campusRepository,times(1)).save(any(CampusEntity.class));
        assertThat(response.getEmail().equals("pablo@maincompany.com"));
    }

    @Test
    void shouldFailWhenIdCompanyNotFound(){
        when(mainCompanyRepository.findById(any(UUID.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> {
            underTest.createCampus(UUID.randomUUID(),CreationUtils.createCampusDTORequest());
        });
    }


    @Test
    void shouldSoftDeleteCampus(){
        when(mainCompanyRepository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createMainCompanyEntity()));
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createCampusEntity()));
        underTest.softDeleteCampus(UUID.randomUUID(), UUID.randomUUID());
        verify(mainCompanyRepository,times(1)).findById(any(UUID.class));
        verify(campusRepository, times(1)).findById(any(UUID.class));
        verify(campusRepository, times(1)).save(any(CampusEntity.class));
    }

    @Test
    void shouldGetAllCampus(){
        when(mainCompanyRepository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createMainCompanyEntity()));
        when(campusRepository.findAllActive(any(UUID.class))).thenReturn(CreationUtils.crateCampusEntityList());
        List<CampusDTOResponse> response = underTest.getAllCampusActive(UUID.fromString("e6616ae0-b732-4ce1-a256-1669a9664fb1"));
        assertNotNull(response);
        assertEquals(response.size(),2);
        verify(campusRepository, times(1)).findAllActive(UUID.fromString("e6616ae0-b732-4ce1-a256-1669a9664fb1"));
    }

    @Test
    void shouldGetOneCampus(){
        when(mainCompanyRepository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createMainCompanyEntity()));
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createCampusEntity()));
        CampusDTOResponse response = underTest.findById(UUID.fromString("e6616ae0-b732-4ce1-a256-1669a9664fb1"), UUID.fromString("68ba3213-1c1c-440b-9cca-8594f536a1bb"));
        assertNotNull(response);
        Assertions.assertThat(response.getEmail().equals("pablo@maincompany.com"));
        verify(mainCompanyRepository, times(1)).findById(any(UUID.class));
        verify(campusRepository, times(1)).findById(any(UUID.class));
    }


}