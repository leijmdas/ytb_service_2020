package ytb.account.htmltest;

import org.springframework.web.bind.annotation.GetMapping;


public class alipayhtml {

    @GetMapping("/html")
    public String html() {
        return "/index.html";
    }

}
