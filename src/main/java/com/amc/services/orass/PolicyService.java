package com.amc.services.orass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amc.model.orass.Policy;
import com.amc.repository.orass.PolicyRepo;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepo policyRepo;

    @Transactional(readOnly = true)
    public List<Policy> findAllPolicy() {
        return this.policyRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Policy findPolicyByCle(String key) {
        return this.policyRepo.findByCleContainingIgnoreCase(key);
    }

    @Transactional(readOnly = true)
    public Policy findByPolNumber(String polNumber) {
        return this.policyRepo.findByPolNumber(polNumber).orElse(null);
    }

    // public List<Policy> findAllPhone(String phone) {
    // return null;
    // }
    @Transactional(readOnly = true)
    public List<Policy> findAllPhone(String phone) {
        return this.policyRepo.findAllPhone(phone);
    }

    @Transactional(readOnly = true)
    public String getPersMoral(String phone) {
        List<Policy> policies = this.policyRepo.findOnePhone(phone);
        for (Policy policy : policies) {
            if (policy != null) {
                return policy.getPers_morale();
            }
        }
        return "NAN"; // Autres ( Personne qui n'a pas des polices dans orass)
    }
}
