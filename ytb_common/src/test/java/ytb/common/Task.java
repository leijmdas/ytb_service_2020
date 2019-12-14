package ytb.common;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
//redis
//https://www.cnblogs.com/zuidongfeng/p/8032505.html

@Component
public class Task {
    @Async
    public void doTestAsync() throws Exception {

           System.out.println("async");

    }

    @Async
    public void doTest() throws Exception {
        System.out.println("doTest******************************************");
    }
}
