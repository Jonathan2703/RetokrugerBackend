package com.jvaldez.retoKruger.Repository;

import com.jvaldez.retoKruger.model.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository  extends JpaRepository<Vaccine, Long> {
    Vaccine findByName(String vaccineName);
}
