package training.com.first;

/**
 * Hello world!
 *
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
@EnableTransactionManagement
@EnableJpaRepositories
public class App 
{
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
		System.out.println("mulai 2");
        return new BCryptPasswordEncoder();
    }

	public static void main(String[] args) {
		System.out.println("mulai 1");
		SpringApplication.run(App.class, args);
	}
}
