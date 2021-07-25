package panterix.CaptionGeneratorJavaAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaptionGeneratorJavaApiApplication {

	public static void main(String[] args) {
		GetCaption.update();
		GetCaption.updatePassword();
		SpringApplication.run(CaptionGeneratorJavaApiApplication.class, args);
	}

}
