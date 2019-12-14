package kunshan.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/common")
@RestController
public class RestDemo {
    @RequestMapping("/kunshan")
    public String demoRest(@RequestBody String data){

        return  data;
    }

}
