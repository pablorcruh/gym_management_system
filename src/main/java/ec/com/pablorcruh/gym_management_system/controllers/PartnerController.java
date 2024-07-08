package ec.com.pablorcruh.gym_management_system.controllers;

import ec.com.pablorcruh.gym_management_system.dto.request.PartnerDTORequest;
import ec.com.pablorcruh.gym_management_system.dto.response.PartnerDTOResponse;
import ec.com.pablorcruh.gym_management_system.services.partner.PartnerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/partner")
public class PartnerController {
    private final PartnerService partnerService;
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping("/campus/{idCampus}")
    public ResponseEntity<PartnerDTOResponse> createPartner(
            @PathVariable(value = "idCampus")UUID idCampus,
            @RequestBody PartnerDTORequest request
            ){
        PartnerDTOResponse response = partnerService.createPartner(idCampus,request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/campus/{idCampus}/partner/{idPartner}")
    public ResponseEntity<Void> deletePartner(
            @PathVariable(value = "idCampus") UUID idCampus,
            @PathVariable(value = "idPartner") UUID idPartner
    ){
        partnerService.softDeletePartner(idCampus, idPartner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/campus/{idCampus}/partner/{idPartner}")
    public ResponseEntity<PartnerDTOResponse> updatePartner(
            @PathVariable(value = "idCampus") UUID idCampus,
            @PathVariable(value = "idPartner") UUID idPartner,
            @RequestBody PartnerDTORequest request
    ){
        PartnerDTOResponse response = partnerService.updatePartner(idCampus,idPartner, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/campus/{idCampus}")
    public ResponseEntity<Page<PartnerDTOResponse>> getAllActiveFromCampusId(
            @PathVariable(value = "idCampus")
            UUID idCampus,
            @RequestParam int page,
            @RequestParam int size
            ){
        Page<PartnerDTOResponse> response = partnerService.getAllActiveByCampusId(idCampus, page, size);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/campus/{idCampus}/partner/{idPartner}")
    public ResponseEntity<PartnerDTOResponse> getPartnerById(
            @PathVariable(value = "idCampus") UUID idCampus,
            @PathVariable(value = "idPartner") UUID idPartner
    ){
        PartnerDTOResponse response= partnerService.findPartnerById(idCampus,idPartner);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
