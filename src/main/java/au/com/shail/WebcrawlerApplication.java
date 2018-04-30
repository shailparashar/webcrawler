package au.com.shail;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WebcrawlerApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebcrawlerApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
