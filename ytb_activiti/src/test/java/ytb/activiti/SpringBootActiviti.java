package ytb.activiti;


import org.springframework.boot.SpringApplication;
import ytb.activiti.rest.RestActiviti;
import ytb.common.test.CorsConfig;


public class SpringBootActiviti {
	public static void main(String[] args) {
		SpringApplication.run(new Class[]{
				CorsConfig.class,
				RestActiviti.class}, args);

	}
}


