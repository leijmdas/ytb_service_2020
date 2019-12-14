package kunshan.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ytb.common.context.model.Ytb_Model;


@PropertySource("classpath:test.properties")
@Component
@ConfigurationProperties("test.kunshan")
public class DemoProperties extends Ytb_Model {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

}
