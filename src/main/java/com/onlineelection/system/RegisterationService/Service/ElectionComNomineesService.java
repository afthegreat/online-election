package com.onlineelection.system.RegisterationService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineelection.system.RegisterationService.Entity.ElectionComNominees;
import com.onlineelection.system.RegisterationService.Repository.ElectionComNomineesRepository;

@Service
public class ElectionComNomineesService {

    @Autowired
    private ElectionComNomineesRepository nomineesRepository;

    public void saveNominees(ElectionComNominees nominees) {
        // Call the repository method to save the nominees into the database
        nomineesRepository.save(nominees);
    }
}
