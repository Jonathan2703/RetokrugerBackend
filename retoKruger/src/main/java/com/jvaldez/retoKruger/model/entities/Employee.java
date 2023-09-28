package com.jvaldez.retoKruger.model.entities;

import com.jvaldez.retoKruger.utils.EcuadorianCI;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long id;

    @NotNull(message = "La identificación es obligatoria")
    @Pattern(regexp = "[0-9]{10}+$", message = "La identificación debe tener 10 dígitos y solo numeros")
    @EcuadorianCI
    @Column(name = "identification", nullable = false, length = 10, unique = true)
    private String identification;

    @NotNull(message = "El nombre es obligatorio")
    @Pattern(regexp = "[A-Za-z ]+$", message = "El nombre solo puede contener letras")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "El apellido es obligatorio")
    @Pattern(regexp = "[A-Za-z ]+$", message = "El apellido solo puede contener letras")
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Pattern(regexp = "(([^<>()\\[\\]\\\\.,;:\\s@”]+(\\.[^<>()\\[\\]\\\\.,;:\\s@”]+)*)|(“.+”))@((\\[[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}])|(([a-zA-Z\\-0–9]+\\.)+[a-zA-Z]{2,}))$", message = "Formato de correo incorrecto")
    @NotNull(message = "El Correo es obligatorio")
    @Email(message = "Debe ser un correo valido")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    @DateTimeFormat(pattern = " yyyy-MM-dd")
    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "vaccinated")
    private Boolean vaccinated;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vaccine")
    private Vaccine vaccine;

    @DateTimeFormat(pattern = " yyyy-MM-dd")
    @Column(name = "vaccination_date")
    private Date vaccinationDate;

    @Column(name = "doses")
    private int doses;
}
