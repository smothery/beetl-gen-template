package panzer.vor.generate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @outhor luozc
 * @create 2018-10-12
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");
        SpringApplication.run(Application.class, args);
    }
}
