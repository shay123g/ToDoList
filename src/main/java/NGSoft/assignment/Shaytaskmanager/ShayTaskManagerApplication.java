package NGSoft.assignment.Shaytaskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"NGSoft.assignment.Shaytaskmanager","NGSoft.assignment.Shaytaskmanager.controller",
//"NGSoft.assignment.Shaytaskmanager.db","NGSoft.assignment.Shaytaskmanager.service","NGSoft.assignment.Shaytaskmanager.utils","NGSoft.assignment.Shaytaskmanager.web"})

public class ShayTaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShayTaskManagerApplication.class, args);
	}

}
