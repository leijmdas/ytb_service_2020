package kunshan.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync//开启异步任务的支持
public class TaskExecutorConfig {

    @Bean("TaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //如果池中的实际线程数小于corePoolSize,无论是否其中有空闲的线程，都会给新的任务产生新的线程
        taskExecutor.setCorePoolSize(5);
        //连接池中保留的最大连接数。
        taskExecutor.setMaxPoolSize(15);
        //queueCapacity 线程池所使用的缓冲队列
        taskExecutor.setQueueCapacity(6000);
        //强烈建议一定要给线程起一个有意义的名称前缀，便于分析日志
        taskExecutor.setThreadNamePrefix("demo Thread-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}