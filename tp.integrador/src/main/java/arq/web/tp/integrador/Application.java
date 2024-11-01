package arq.web.tp.integrador;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		Sentry.init();
		SpringApplication.run(Application.class, args);
	}

}
