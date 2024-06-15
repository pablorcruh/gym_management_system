package ec.com.pablorcruh.gym_management_system.services.main_company;

import ec.com.pablorcruh.gym_management_system.dto.converter.MainCompanyConverter;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;
import ec.com.pablorcruh.gym_management_system.models.MainCompanyEntity;
import ec.com.pablorcruh.gym_management_system.repository.MainCompanyRepository;
import ec.com.pablorcruh.gym_management_system.util.CreationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainCompanyServiceImplTest {

    MainCompanyServiceImpl underTest;
    @Mock
    MainCompanyRepository repository;
    @Mock
    MainCompanyConverter converter;
    @Mock
    ModelMapper modelMapper;
    @BeforeEach
    void setup(){
        modelMapper = new ModelMapper();
        converter= new MainCompanyConverter(modelMapper);
        underTest = new MainCompanyServiceImpl(repository, converter);
    }

    @Test
    void shouldCreateMainCompany(){
        when(repository.save(any(MainCompanyEntity.class))).thenReturn(CreationUtils.createMainCompanyEntity());
        MainCompanyDTOResponse response = underTest.createMainCompany(CreationUtils.createMainCompanyDTOResponse());
        assertNotNull(response);
        verify(repository, times(1)).save(any(MainCompanyEntity.class));

    }

}