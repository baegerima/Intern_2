package com.example.inturn_2.services;

import com.example.inturn_2.entities.SharedCompetence;
import com.example.inturn_2.repositories.SharedCompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SharedCompetenceService {
    @Autowired
    private SharedCompetenceRepository sharedCompetenceRepository;

    public List<SharedCompetence> getAllSharedCompetences() {
        return sharedCompetenceRepository.findAll();
    }

    public Optional<SharedCompetence> getSharedCompetenceById(int id) {
        return sharedCompetenceRepository.findById(id);
    }

    public SharedCompetence createSharedCompetence(SharedCompetence sharedCompetence) {
        return sharedCompetenceRepository.save(sharedCompetence);
    }

    public SharedCompetence updateSharedCompetence(int id, SharedCompetence updatedSharedCompetence) {
        Optional<SharedCompetence> existingCompetence = sharedCompetenceRepository.findById(id);

        if (existingCompetence.isPresent()) {
            SharedCompetence competence = existingCompetence.get();
            competence.setName(updatedSharedCompetence.getName());  // Update the fields accordingly
            return sharedCompetenceRepository.save(competence);
        } else {
            return null;  // Or throw an exception depending on your error handling strategy
        }
    }


    public void deleteSharedCompetence(int id) {
        sharedCompetenceRepository.deleteById(id);
    }
}