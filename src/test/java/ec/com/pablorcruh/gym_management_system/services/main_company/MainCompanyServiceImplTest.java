package ec.com.pablorcruh.gym_management_system.services.main_company;

import ec.com.pablorcruh.gym_management_system.dto.converter.CampusConverter;
import ec.com.pablorcruh.gym_management_system.dto.converter.MainCompanyConverter;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.MainCompanyEntity;
import ec.com.pablorcruh.gym_management_system.repository.MainCompanyRepository;
import ec.com.pablorcruh.gym_management_system.util.CreationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainCompanyServiceImplTest {

    MainCompanyServiceImpl underTest;
    @Mock
    MainCompanyRepository repository;
    @Mock
    MainCompanyConverter mainCompanyConverter;
    @Mock
    CampusConverter campusConverter;
    @Mock
    ModelMapper modelMapper;
    @BeforeEach
    void setup(){
        modelMapper = new ModelMapper();
        campusConverter = new CampusConverter(modelMapper);
        mainCompanyConverter = new MainCompanyConverter(modelMapper, campusConverter);
        underTest = new MainCompanyServiceImpl(repository, mainCompanyConverter);
    }

    @Test
    void shouldCreateMainCompany(){
        when(repository.save(any(MainCompanyEntity.class))).thenReturn(CreationUtils.createMainCompanyEntity());
        MainCompanyDTOResponse response = underTest.createMainCompany(CreationUtils.createMainCompanyDTORequest());
        assertNotNull(response);
        verify(repository, times(1)).save(any(MainCompanyEntity.class));
        assertThat(response.getEmail().equals("pablo@maincompany.com"));
    }

    @Test
    void shouldSoftDeleteMainCompany(){
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createMainCompanyEntity()));
        underTest.deleteMainCompany(UUID.randomUUID());
        verify(repository, times(1)).save(any(MainCompanyEntity.class));
        verify(repository, times(1)).findById(any(UUID.class));
    }

    @Test
    void shouldUpdateMainCompany(){
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createMainCompanyEntity()));
        when(repository.save(any(MainCompanyEntity.class))).thenReturn(CreationUtils.createMainCompanyEntity());
        MainCompanyDTOResponse response = underTest.updateMainCompany(UUID.randomUUID(),CreationUtils.createMainCompanyDTORequest());
        verify(repository,times(1)).save(any(MainCompanyEntity.class));
        verify(repository,times(1)).findById(any(UUID.class));
        assertThat(response.getEmail().equals("pablo@maincompany.com"));
    }

    @Test
    void shouldGetAllMainCompanies(){
        when(repository.findAllMainCompanyActive()).thenReturn(CreationUtils.createMainCompanyEntityList());
        List<MainCompanyDTOResponse> response = underTest.getAllActive();
        assertNotNull(response);
        assertEquals(response.size(),2);
        verify(repository, times(1)).findAllMainCompanyActive();
    }

    @Test
    void shouldGetOneMainCompanies(){
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(CreationUtils.createMainCompanyEntity()));
        MainCompanyDTOResponse response = underTest.findById(UUID.randomUUID());
        assertNotNull(response);
        assertThat(response.getEmail().equals("pablo@maincompany.com"));
        verify(repository, times(1)).findById(any(UUID.class));
    }



}