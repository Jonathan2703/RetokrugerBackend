package com.jvaldez.retoKruger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
	@Info(
			title = "Reto Kruger", version = "1.0", description = "Manejo de Empleados y Vacunas"

	),
		servers = {
		@Server(
				description = "Local ENV",
				url = "http://localhost:8080"
		)
}

)
public class RetoKrugerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetoKrugerApplication.class, args);
	}

}
