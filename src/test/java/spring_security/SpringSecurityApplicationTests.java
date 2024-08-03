package spring_security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring_security.Models.User.AppRole;
import spring_security.Models.User.AppUser;
import spring_security.Services.User.UserService;

import java.util.ArrayList;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
		AppUser appUser = AppUser.builder()
				.user_id(3L)
				.username("Diallo")
				.password("123")
				.enabled(false)
				.appRoles(new ArrayList<>())
				.build();

		userService.saveUser(appUser);
	}

}
