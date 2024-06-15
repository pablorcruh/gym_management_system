package ec.com.pablorcruh.gym_management_system.controllers;

import ec.com.pablorcruh.gym_management_system.dto.request.MainCompanyDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;
import ec.com.pablorcruh.gym_management_system.services.main_company.MainCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maincompany")
public class MainCompanyController {

    private final MainCompanyService service;

    public MainCompanyController(MainCompanyService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<MainCompanyDTOResponse> createMainCompany(
            @RequestBody MainCompanyDTORequest request
            ){
        MainCompanyDTOResponse response = service.createMainCompany(request);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
