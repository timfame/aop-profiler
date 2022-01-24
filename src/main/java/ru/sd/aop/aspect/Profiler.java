package ru.sd.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
public class Profiler {

    private final Map<String, List<Long>> invokedMethods = new HashMap<>();

    @Around("execution (* ru.sd.aop.domain..*.*(..))")
    public Object profileMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long starts = System.nanoTime();
        Object result = joinPoint.proceed(joinPoint.getArgs());
        long ends = System.nanoTime();

        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "/" + joinPoint.getSignature().getName();

        invokedMethods.computeIfAbsent(methodName, k -> new ArrayList<>()).add(ends - starts);

        return result;
    }

    public void printStatistic() {
        for (Map.Entry<String, List<Long>> e : invokedMethods.entrySet()) {
            String methodName = e.getKey();
            List<Long> timings = e.getValue();

            int count = timings.size();
            long summaryTime = 0;
            for (Long timing : timings) {
                summaryTime += timing;
            }
            double averageTime = (double) summaryTime / (double) count;

            System.out.println();
            System.out.println(methodName + ":");
            System.out.println(" - Invoke times: " + count);
            System.out.println(" - Summary execution time: " + summaryTime);
            System.out.println(" - Average execution time: " + averageTime);
        }
    }

    public void clear() {
        invokedMethods.clear();
    }
}
