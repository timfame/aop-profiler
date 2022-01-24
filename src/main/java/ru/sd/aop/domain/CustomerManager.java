package ru.sd.aop.domain;

/**
 * @author akirakozov
 */
public interface CustomerManager {
    int addCustomer(Customer customer);
    Customer findCustomer(int id);
    int calcValuesSum();
    Customer getCustomerWithMaxValue();
    int size();
}
