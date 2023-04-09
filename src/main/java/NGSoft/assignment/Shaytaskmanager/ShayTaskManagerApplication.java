package NGSoft.assignment.Shaytaskmanager;

import NGSoft.assignment.Shaytaskmanager.service.UserService;
import NGSoft.assignment.Shaytaskmanager.web.UserWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ShayTaskManagerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ShayTaskManagerApplication.class, args);

		UserWebRequest defaultUser = new UserWebRequest();
		defaultUser.setName("SYS");
		defaultUser.setIsActive(true);
		defaultUser.setIsAdmin(true);
		defaultUser.setEmail("default@myorg.com");
		defaultUser.setPassword("AAAaaa123");
		context.getBean(UserService.class).addUser(defaultUser);
	}


}
