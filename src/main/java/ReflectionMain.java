import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sg.sq")
public class ReflectionMain {
    public static void main(String[] args) {
        SpringApplication.run(ReflectionMain.class,args);
    }
}
