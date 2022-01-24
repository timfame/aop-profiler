package ru.sd.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.sd.aop.aspect.Profiler;
import ru.sd.aop.dao.CustomerInMemoryDao;
import ru.sd.aop.domain.CustomerManager;
import ru.sd.aop.domain.CustomerManagerImpl;

/**
 * @author akirakozov
 */
@Configuration
@EnableAspectJAutoProxy
public class ContextConfiguration {
    @Bean
    public CustomerManager customerManager() {
        return new CustomerManagerImpl(new CustomerInMemoryDao());
    }

    @Bean
    public Profiler aspect() {
        return new Profiler();
    }
}
