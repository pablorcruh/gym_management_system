package ec.com.pablorcruh.gym_management_system;


import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.Registry;
import io.jkratz.mediator.spring.SpringMediator;
import io.jkratz.mediator.spring.SpringRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class GymManagementSystemApplication {

    private final ApplicationContext applicationContext;

    @Autowired
    public GymManagementSystemApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Bean
    public Registry registry() {
        return new SpringRegistry(applicationContext);
    }

    @Bean
    public Mediator mediator(Registry registry) {
        return new SpringMediator(registry);
    }



    public static void main(String[] args) {
        SpringApplication.run(GymManagementSystemApplication.class, args);
    }

}
