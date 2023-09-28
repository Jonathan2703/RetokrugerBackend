package com.jvaldez.retoKruger.service;

import com.jvaldez.retoKruger.Repository.EmployeeRepository;
import com.jvaldez.retoKruger.model.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findByIdentification(String identification) {
        return employeeRepository.findByIdentification(identification);
    }

    @Override
    public Employee updateStateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findByVaccinated(boolean vaccinated) {
        return employeeRepository.findByVaccinated(vaccinated);
    }

    @Override
    public List<Employee> findByVaccine(String vaccine) {
        return employeeRepository.findByVaccine(vaccine);
    }

    @Override
    public List<Employee> findByVaccinationDate(Date date1, Date date2) {
        return employeeRepository.findByVaccinationDate(date1, date2);
    }

}
