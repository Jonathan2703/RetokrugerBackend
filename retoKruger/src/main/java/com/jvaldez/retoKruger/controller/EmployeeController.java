package com.jvaldez.retoKruger.controller;


import com.jvaldez.retoKruger.model.entities.Employee;
import com.jvaldez.retoKruger.model.entities.Vaccine;
import com.jvaldez.retoKruger.service.EmployeeService;
import com.jvaldez.retoKruger.service.RoleService;
import com.jvaldez.retoKruger.service.VaccineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
@ApiResponse(description = "Api para gestionar los empleados")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private VaccineService vaccineService;
    @PutMapping("/{identificacion}")
    @PreAuthorize("hasRole('EMPLEADO')")
    @Operation(summary = "Actualizar un empleado", description = "Actualiza un empleado", tags = {"employee"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Empleado actualizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Empleado no encontrado")}
    )
    public ResponseEntity<Object> updateEmployee(@PathVariable String identificacion,
                                                 @RequestBody @Validated Employee employee) {
        Employee employeeSaved = employeeService.findByIdentification(identificacion);
        if (employeeSaved == null) {
            return ResponseEntity.notFound().build();
        }
        employeeSaved.setName(employee.getName());
        employeeSaved.setLastname(employee.getLastname());
        employeeSaved.setEmail(employee.getEmail());
        employeeSaved
                .setBirthdate(employee.getBirthdate() != null ? employee.getBirthdate() : employeeSaved.getBirthdate());
        employeeSaved.setAddress(employee.getAddress() != null ? employee.getAddress() : employeeSaved.getAddress());
        employeeSaved.setMobilePhone(
                employee.getMobilePhone() != null ? employee.getMobilePhone() : employeeSaved.getMobilePhone());

        if (employee.getVaccinated() != null && employee.getVaccinated()) {
            if (employee.getDoses() == 0 || employee.getVaccinationDate() == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "Debe ingresar los datos de la vacuna"));
            }
            Vaccine vaccine = vaccineService.getVaccineByName(employee.getVaccine().getName());
            if (vaccine == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "La vacuna no existe"));
            }
            employeeSaved.setVaccine(vaccine);
            employeeSaved.setDoses(employee.getDoses());
            employeeSaved.setVaccinationDate(employee.getVaccinationDate());
            employeeSaved.setVaccinated(true);

        }

        return ResponseEntity.ok(employeeService.updateEmployee(employeeSaved));
    }

}
