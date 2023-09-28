package com.jvaldez.retoKruger.controller;

import com.jvaldez.retoKruger.model.entities.Employee;
import com.jvaldez.retoKruger.model.entities.Role;
import com.jvaldez.retoKruger.model.entities.UserEntity;
import com.jvaldez.retoKruger.model.enums.ERol;
import com.jvaldez.retoKruger.service.EmployeeService;
import com.jvaldez.retoKruger.service.RoleService;
import com.jvaldez.retoKruger.service.VaccineService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private VaccineService vaccineService;

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(summary = "Crear un empleado", description = "Crea un empleado y su usuario", tags = {"user"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Empleado creado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Empleado ya existe")}
    )
    public ResponseEntity<Object> createEmployee(@RequestBody @Validated Employee employee){
        Employee employeeS = employeeService.findByIdentification(employee.getIdentification());
        if (employeeS != null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message",
                            "Ya existe un empleado con la identificacion " + employee.getIdentification()));
        }
        String username = generateUsername(employee);
        String password = generatePassword();
        Role role = roleService.getRoleByName(ERol.EMPLEADO.name());
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(new BCryptPasswordEncoder().encode(password));
        userEntity.setRole(role);
        employee.setUserEntity(userEntity);
        employee.setVaccinated(false);
        Employee employeeSaved = employeeService.saveEmployee(employee);
        employeeSaved.getUserEntity().setPassword(password);
        return ResponseEntity.ok(employeeSaved);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(summary = "Obtener todos los empleados", description = "Obtiene todos los empleados", tags = {"user"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Empleados obtenidos")}
    )
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{identification}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(summary = "Obtener un empleado", description = "Obtiene un empleado por su identificacion", tags = {"user"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Empleado obtenido"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Empleado no encontrado")}
    )
    public ResponseEntity<Employee> findByIdentification(@PathVariable String identification) {
        return ResponseEntity.ok(employeeService.findByIdentification(identification));
    }

    @DeleteMapping("/{identificacion}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(summary = "Eliminar un empleado", description = "Elimina un empleado por su identificacion")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable String identificacion) {
        Employee employeeSaved = employeeService.findByIdentification(identificacion);
        if (employeeSaved == null) {
            return ResponseEntity.notFound().build();
        }
        employeeSaved.setDeleted(true);
        employeeService.updateEmployee(employeeSaved);
        return ResponseEntity.ok(Map.of("deleted", true));
    }

    @GetMapping("/vaccinated/{vaccinated}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(summary = "Obtener todos los empleados vacunados", description = "Obtiene todos los empleados vacunados", tags = {"user"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Empleados obtenidos")}
    )
    public ResponseEntity<List<Employee>> getAllEmployeesByVaccinated(@PathVariable Boolean vaccinated) {
        return ResponseEntity.ok(employeeService.findByVaccinated(vaccinated));
    }

    @GetMapping("/vaccine/{vaccine}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(summary = "Obtener todos los empleados por vacuna", description = "Obtiene todos los empleados por vacuna", tags = {"user"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Empleados obtenidos")}
    )
    public ResponseEntity<List<Employee>> getAllEmployeesByVaccine(@PathVariable String vaccine) {
        return ResponseEntity.ok(employeeService.findByVaccine(vaccine));
    }


    @GetMapping("/date/{date1}/{date2}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Operation(summary = "Obtener todos los empleados por fecha de vacunacion", description = "Obtiene todos los empleados por fecha de vacunacion", tags = {"user"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Empleados obtenidos")}
    )
    public ResponseEntity<List<Employee>> getAllEmployeesByDate(@PathVariable String date1, @PathVariable String date2) {
        Date dateOne = new Date();
        Date dateTwo = new Date();
        try {
            dateOne = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
            dateTwo = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(employeeService.findByVaccinationDate(dateOne, dateTwo));

    }


    public static String generateUsername(Employee empleado) {
        String[] nombres = empleado.getName().split(" ");
        String[] apellidos = empleado.getLastname().split(" ");
        String username = nombres[0].toLowerCase()
                + apellidos[0].toLowerCase()
                + String.valueOf(empleado.getId());
        return username;
    }

    public static String generatePassword() {
        String password = "";
        for (int i = 0; i < 8; i++) {
            password += (char) (Math.random() * 89 + 33);
        }
        return password;
    }

}
