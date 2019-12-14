package ytb.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:test.jdbc.properties")
public class AppConfig {
    @Autowired Environment env;
   //  return new MyBean(env.getProperty("bean.name"));

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Value("${common.url}")
    String url;

}