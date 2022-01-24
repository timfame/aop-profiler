package ru.sd.aop.domain;

/**
 * @author akirakozov
 */
public class Customer {
    public final String name;
    public final int value;

    public Customer(String name) {
        this.name = name;
        this.value = (int) (Math.random() * 1000);
    }
}
