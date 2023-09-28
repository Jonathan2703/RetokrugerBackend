package com.jvaldez.retoKruger.service;

import com.jvaldez.retoKruger.model.entities.Vaccine;

import java.util.List;

public interface VaccineService {
    public Vaccine getVaccineById(Long id);

    public List<Vaccine> getAllVaccines();

    public Vaccine getVaccineByName(String name);
}
