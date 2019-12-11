package com.adechinan.sbreservationsystem;

import com.adechinan.sbreservationsystem.authentication.Role;
import com.adechinan.sbreservationsystem.authentication.RoleName;
import com.adechinan.sbreservationsystem.authentication.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
/*
@EntityScan(basePackageClasses = {
		SbReservationSystemApplication.class,
		Jsr310JpaConverters.class
})
*/
public class SbReservationSystemApplication {

	/*@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(SbReservationSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RoleRepository repository) {

		return (args) -> {
			repository.save(new Role(RoleName.ROLE_ADMIN));
			repository.save(new Role(RoleName.ROLE_USER));

		};

	}

}
