package ru.sd.aop.domain;

import ru.sd.aop.dao.CustomerInMemoryDao;

import java.util.Map;

/**
 * @author akirakozov
 */
public class CustomerManagerImpl implements CustomerManager {
    CustomerInMemoryDao customerDao = new CustomerInMemoryDao();

    public CustomerManagerImpl(CustomerInMemoryDao customerDao) {
        this.customerDao = customerDao;
    }

    public int addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    public Customer findCustomer(int id) {
        return customerDao.findCustomer(id);
    }

    public int calcValuesSum() {
        return customerDao.calcValuesSum();
    }

    public Customer getCustomerWithMaxValue() {
        return customerDao.getCustomerWithMaxValue();
    }

    @Override
    public int size() {
        return customerDao.size();
    }
}
