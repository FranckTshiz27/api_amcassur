package com.amc.controller.orass;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.amc.model.orass.Risk;
import com.amc.routes.Routes;
import com.amc.services.orass.RiskService;

import java.util.List;
@RequestMapping(path = Routes.BASE_URL_MY_AMC+ "risk")
@RestController
@AllArgsConstructor
public class RiskController {

    private RiskService riskService;

    @GetMapping(path = "v1/find-all")
    public List<Risk> findAll() {
        return this.riskService.findAll();
    }

    // @GetMapping(path = "v1/find-by-matricule/{riskId}")
    // public Risk findByImmatriculation(@PathVariable(name = "riskId") String
    // immatriculation) {
    // return this.riskService.findByImmatriculation(immatriculation);
    // }

    @GetMapping(path = "v1/find-by-phone/{phone}")
    public ResponseEntity<List<Risk>> findByPhone(@PathVariable(name = "phone") String phone) {
        List<Risk> risks = this.riskService.findByPhone(phone);
        return ResponseEntity.ok().body(risks);
    }

    @GetMapping(path = "v1/find-by-matricule/{matricule}/{phone}")
    public ResponseEntity<List<Risk>> findByMatricule(@PathVariable(name = "matricule") String matricule,
            @PathVariable(name = "phone") String phone) {
        List<Risk> risks = this.riskService.findAllByImmatriculation(matricule, phone);
        return ResponseEntity.ok().body(risks);
    }

}
