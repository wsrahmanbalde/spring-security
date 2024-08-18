package spring_security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring_security.Models.AppUser;
import spring_security.Services.User.AccountService;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired
	AccountService accountService;

	@Test
	void contextLoads() {
		AppUser appUser = AppUser.builder()
				.user_id(1L)
				.username("test")
				.password("123")
				.build();

		accountService.addNewUser(appUser);
	}

}
