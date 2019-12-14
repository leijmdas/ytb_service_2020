package ytb.log;


import org.springframework.boot.SpringApplication;
import ytb.common.test.CorsConfig;
import ytb.log.notify.rest.RestTaskLog;
import ytb.log.smslog.rest.RestSmsLog;

public class SpringBootTaskLog {
	public static void main(String[] args) {
		SpringApplication.run(new Class[]{
				CorsConfig.class,
				RestTaskLog.class,
				RestSmsLog.class}, args);

	}
}


