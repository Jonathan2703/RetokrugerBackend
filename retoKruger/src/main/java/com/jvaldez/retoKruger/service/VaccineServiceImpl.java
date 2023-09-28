package com.jvaldez.retoKruger.service;

import com.jvaldez.retoKruger.Repository.VaccineRepository;
import com.jvaldez.retoKruger.model.entities.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService{

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public Vaccine getVaccineById(Long id) {
        return vaccineRepository.findById(id).get();
    }

    @Override
    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

    @Override
    public Vaccine getVaccineByName(String name) {
        return vaccineRepository.findByName(name);
    }

}
