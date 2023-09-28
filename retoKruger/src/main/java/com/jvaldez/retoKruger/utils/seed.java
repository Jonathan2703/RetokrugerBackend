package com.jvaldez.retoKruger.utils;
import com.jvaldez.retoKruger.Repository.RoleRepository;
import com.jvaldez.retoKruger.Repository.UserRepository;
import com.jvaldez.retoKruger.Repository.VaccineRepository;
import com.jvaldez.retoKruger.model.entities.Role;
import com.jvaldez.retoKruger.model.entities.UserEntity;
import com.jvaldez.retoKruger.model.entities.Vaccine;
import com.jvaldez.retoKruger.model.enums.ERol;
import com.jvaldez.retoKruger.model.enums.EVacinne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class seed implements ApplicationRunner{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Vaccine vaccine1 = Vaccine.builder()
                .name(EVacinne.AstraZeneca.name())
                .build();
        vaccineRepository.save(vaccine1);

        Vaccine vaccine2 = Vaccine.builder()
                .name(EVacinne.Pfizer.name())
                .build();
        vaccineRepository.save(vaccine2);

        Vaccine vaccine3 = Vaccine.builder()
                .name(EVacinne.SPUTNIK.name())
                .build();
        vaccineRepository.save(vaccine3);

        Vaccine vaccine4 = Vaccine.builder()
                .name(EVacinne.JhonsonAndJhonson.name())
                .build();
        vaccineRepository.save(vaccine4);

        Role role2 = Role.builder()
                .name(ERol.EMPLEADO.name())
                .build();
        roleRepository.save(role2);



        UserEntity userEntity = UserEntity.builder()
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .role(Role.builder()
                        .name(ERol.ADMINISTRADOR.name())
                        .build())
                .build();
        userRepository.save(userEntity);
    }
}
