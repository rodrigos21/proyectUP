package arq.web.tp.integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var var1 = System.getenv("DB_URL");
		var var2 = System.getenv("DB_USERNAME");
		var var3 = System.getenv("DB_PASSWORD");
		var var4 = System.getenv("MYSQL_ROOT_PASSWORD");
		var var5 = System.getenv("MYSQL_PASSWORD");
		var var6 = System.getenv("MYSQL_USER");
		var var7 = System.getenv("MYSQL_DATABASE");

		System.out.println("######### INIT PRINTS ########## ");
		System.out.println("DB_URL: " + var1);
		System.out.println("DB_USERNAME: " + var2);
		System.out.println("DB_PASSWORD: " + var3);
		System.out.println("MYSQL_ROOT_PASSWORD: " + var4);
		System.out.println("MYSQL_PASSWORD: " + var5);
		System.out.println("MYSQL_USER: " + var6);
		System.out.println("MYSQL_DATABASE: " + var7);
		System.out.println("######### END PRINTS ########## ");
		SpringApplication.run(Application.class, args);
	}

}
