package com.amc.controller.orass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amc.model.orass.Policy;
import com.amc.routes.Routes;
import com.amc.services.orass.PolicyService;

import lombok.AllArgsConstructor;

@RequestMapping(path = Routes.BASE_URL_MY_AMC + "policy")
@RestController
// @CrossOrigin("*")
@AllArgsConstructor
public class PolicyController {

    @Autowired
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

    // api/amcassur/police/v1/find-by-phone/{phone}
    // api/amcassur/policy/v1/find-by-phone/893537773
    @GetMapping(path = "v1/find-by-phone/{phone}")
    public ResponseEntity<List<Policy>> findAllByPhone(@PathVariable(name = "phone") String phone) {
        List<Policy> policies;
        policies = this.policyService.findAllPhone(phone);
        return ResponseEntity.ok().body(policies);
    }

}
