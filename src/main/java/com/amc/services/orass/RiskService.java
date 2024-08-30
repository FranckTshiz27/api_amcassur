package com.amc.services.orass;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amc.model.orass.Risk;
import com.amc.repository.orass.RiskRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class RiskService {

    @Autowired
    private RiskRepo riskRepo;

    @Transactional(readOnly = true)
    public List<Risk> findAll() {
        return this.riskRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Risk> findAllByImmatriculation(String matricule, String phone) {
        return this.riskRepo.findAllByImmatriculation(matricule, phone);
    }

    @Transactional(readOnly = true)
    public List<Risk> findByPhone(String phone) {
        return this.riskRepo.findAllByPhone(phone);
    }

    @Transactional(readOnly = true)
    public List<Risk> findByKey(String key) {
        return this.riskRepo.findByCle(key);
    }

    @Transactional(readOnly = true)
    public Risk findByKeyAndImmat(String key, String immat) {
        // return this.riskRepo.findByCleAndRisk( key, immat );
        return this.riskRepo.findByPolAndRisk(key, immat).orElse(null);
    }

    @Transactional(readOnly = true)
    public Risk findByPolAndImmat(String pol, String immat) {
        // return this.riskRepo.findByCleAndRisk( key, immat );
        return this.riskRepo.findByPolAndRisk(pol, immat).orElse(null);
    }

    @Transactional(readOnly = true)
    public Risk findByImmatriculation(String numeImmat) {
        return this.riskRepo.findByImmatriculation(numeImmat).orElse(null);
    }

}
