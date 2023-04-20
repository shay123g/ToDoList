package NGSoft.assignment.Shaytaskmanager;

import NGSoft.assignment.Shaytaskmanager.config.RsaProp;
import NGSoft.assignment.Shaytaskmanager.config.SecurityConfig;
import NGSoft.assignment.Shaytaskmanager.service.MiscService;
import NGSoft.assignment.Shaytaskmanager.service.UserService;
import NGSoft.assignment.Shaytaskmanager.web.UserWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableConfigurationProperties(RsaProp.class)
@SpringBootApplication
public class ShayTaskManagerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ShayTaskManagerApplication.class, args);

		/**
		 * create default Admin user in initialization
		 */
		UserWebRequest defaultUser = new UserWebRequest();
		defaultUser.setName("SYS");
		defaultUser.setIsActive(true);
		defaultUser.setIsAdmin(true);
		defaultUser.setEmail("default@myorg.com");
		defaultUser.setPassword("AAAaaa123");
		defaultUser.setRequester("SYSTEM");
		context.getBean(UserService.class).addUser(defaultUser);
	}
//	@Bean
//	public InMemoryUserDetailsManager users() {
//		return new InMemoryUserDetailsManager(
//				User.withUsername("shay")
//						.password("{noop}1234")  //{noop} - using No PasswordEncoder
//						.authorities("read")
//						.build()
//		);
//	}


}
