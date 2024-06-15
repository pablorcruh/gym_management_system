package ec.com.pablorcruh.gym_management_system;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class GymManagementSystemApplication {

    private final ApplicationContext applicationContext;

    @Autowired
    public GymManagementSystemApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    public static void main(String[] args) {
        SpringApplication.run(GymManagementSystemApplication.class, args);
    }

}
