package ru.sd.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sd.aop.aspect.Profiler;
import ru.sd.aop.domain.CustomerManager;
import ru.sd.aop.simulator.Simulator;

/**
 * @author akirakozov
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        CustomerManager customerManager = ctx.getBean(CustomerManager.class);
        Profiler profiler = ctx.getBean(Profiler.class);

        Simulator simulator = new Simulator(customerManager);

        simulator.simulate(100000);

        profiler.printStatistic();

        profiler.clear();
    }
}
