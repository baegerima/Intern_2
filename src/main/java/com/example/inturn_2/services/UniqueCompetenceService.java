package com.example.inturn_2.services;

import com.example.inturn_2.entities.UniqueCompetence;
import com.example.inturn_2.repositories.UniqueCompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniqueCompetenceService {
    @Autowired
    private UniqueCompetenceRepository uniqueCompetenceRepository;

    public List<UniqueCompetence> getAllUniqueCompetences() {
        return uniqueCompetenceRepository.findAll();
    }

    public Optional<UniqueCompetence> getUniqueCompetenceById(int id) {
        return uniqueCompetenceRepository.findById(id);
    }

    public UniqueCompetence createUniqueCompetence(UniqueCompetence uniqueCompetence) {
        return uniqueCompetenceRepository.save(uniqueCompetence);
    }

    public UniqueCompetence updateUniqueCompetence(int id, UniqueCompetence updatedUniqueCompetence) {
        Optional<UniqueCompetence> existingCompetence = uniqueCompetenceRepository.findById(id);

        if (existingCompetence.isPresent()) {
            UniqueCompetence competence = existingCompetence.get();
            competence.setName(updatedUniqueCompetence.getName());  // Update the fields accordingly
            return uniqueCompetenceRepository.save(competence);
        } else {
            return null;  // Or throw an exception depending on your error handling strategy
        }
    }


    public void deleteUniqueCompetence(int id) {
        uniqueCompetenceRepository.deleteById(id);
    }
}