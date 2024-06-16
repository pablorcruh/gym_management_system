package ec.com.pablorcruh.gym_management_system.controllers;

import ec.com.pablorcruh.gym_management_system.dto.request.MainCompanyDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.MainCompanyDTOResponse;
import ec.com.pablorcruh.gym_management_system.services.main_company.MainCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMainCompany(@PathVariable(value = "id")UUID id){
        service.deleteMainCompany(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MainCompanyDTOResponse> updateMainCompany(
            @PathVariable(value = "id") UUID id,
            @RequestBody MainCompanyDTORequest request
            ){
        MainCompanyDTOResponse response = service.updateMainCompany(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<MainCompanyDTOResponse>> getAllActive(){
        List<MainCompanyDTOResponse> response = service.getAllActive();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainCompanyDTOResponse> getById(
            @PathVariable(value = "id")UUID id
    ){
        MainCompanyDTOResponse response = service.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
