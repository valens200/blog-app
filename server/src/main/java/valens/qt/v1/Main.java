package valens.qt.v1;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import valens.qt.v1.annotations.EnableCors;
import valens.qt.v1.models.Role;
import valens.qt.v1.models.enums.ERole;
import valens.qt.v1.repositories.IRoleRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@EnableCors
public class Main extends SpringBootServletInitializer {
	private final IRoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	@Bean
	public void  initializeRoles(){
		Role roleEntity = new Role();
		List<ERole> roles = new ArrayList<>();
		roles.add(ERole.ADMIN);
		roles.add(ERole.USER);
		for(ERole role : roles){
			if(!roleRepository.existsByRoleName(role.name())){
				roleEntity.setRoleName(role.name());
				roleRepository.save(roleEntity);
			}
		}

	}
}