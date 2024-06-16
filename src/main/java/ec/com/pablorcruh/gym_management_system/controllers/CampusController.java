package ec.com.pablorcruh.gym_management_system.controllers;

import ec.com.pablorcruh.gym_management_system.dto.request.CampusDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.CampusDTOResponse;
import ec.com.pablorcruh.gym_management_system.services.campus.CampusService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/campus")
public class CampusController {

    private final CampusService campusService;

    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    @PostMapping("/{mainCompanyId}")
    public ResponseEntity<CampusDTOResponse> createCampus(
            @PathVariable(value = "mainCompanyId") UUID mainCompanyId,
            @RequestBody CampusDTORequest request
            ){
       CampusDTOResponse response = campusService.createCampus(mainCompanyId,request);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/maincompany/{idMainCompany}/campus/{idCampus}")
    public ResponseEntity<Void> deleteCampus(
            @PathVariable(value = "idMainCompany") UUID idMainCompany,
            @PathVariable(value = "idCampus") UUID idCampus
    ){
        campusService.softDeleteCampus(idMainCompany, idCampus);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/maincompany/{idMainCompany}/campus/{idCampus}")
    public ResponseEntity<CampusDTOResponse> updateCampus(
            @PathVariable(value = "idMainCompany") UUID idMainCompany,
            @PathVariable(value = "idCampus") UUID idCampus,
            @RequestBody CampusDTORequest request
    ){
        CampusDTOResponse response = campusService.updateCampus(idMainCompany, idCampus, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/maincompany/{idMainCompany}")
    public ResponseEntity<List<CampusDTOResponse>> getAllCampus(
            @PathVariable(value = "idMainCompany") UUID idMainCompany
    ){
        List<CampusDTOResponse> response = campusService.getAllCampusActive(idMainCompany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/maincompany/{idMainCompany}/campus/{idCampus}")
    public ResponseEntity<CampusDTOResponse> getById(
            @PathVariable(value = "idMainCompany") UUID idMainCompany,
            @PathVariable(value = "idCampus") UUID idCampus
            ){
        CampusDTOResponse response = campusService.findById(idMainCompany, idCampus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
