package com.amc.controller.orass;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.amc.model.orass.Policy;
import com.amc.routes.Routes;
import com.amc.services.orass.PolicyService;
import java.util.List;

@RequestMapping(path = Routes.BASE_URL_MY_AMC)
@RestController
// @CrossOrigin("*")
@AllArgsConstructor
public class PolicyController {

    private PolicyService policyService;

  


    @GetMapping(path = "find-all")
    public List<Policy> findAll() {
        return this.policyService.findAllPolicy();
    }

    @GetMapping(path = "find/key/{polNumber}")
    public Policy findByPolicy(@PathVariable(name = "polNumber") String polNumber) {
        return this.policyService.findByPolNumber(polNumber);
    }

  

    @GetMapping(path = "v1/find-one/{phone}")
    public ResponseEntity<String> findOnePhone(@PathVariable(name = "phone") String phone) {
        String pers_moral = this.policyService.getPersMoral(phone);
        return ResponseEntity.ok().body(pers_moral);
    }

}