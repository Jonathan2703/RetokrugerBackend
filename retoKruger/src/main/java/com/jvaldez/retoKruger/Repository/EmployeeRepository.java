package com.jvaldez.retoKruger.Repository;

import com.jvaldez.retoKruger.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByIdentification(String identification);

    List<Employee> findByVaccinated(boolean vaccinated);

    @Query("SELECT e FROM Employee e WHERE e.vaccine.name = ?1")
    List<Employee> findByVaccine(String vaccine);

    @Query("SELECT e FROM Employee e WHERE e.vaccinationDate BETWEEN ?1 AND ?2")
    List<Employee> findByVaccinationDate(Date date1, Date date2);
}
