package ru.sd.aop.dao;

import ru.sd.aop.domain.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author akirakozov
 */
public class CustomerInMemoryDao {
    private static AtomicInteger currentId = new AtomicInteger(1);
    private HashMap<Integer, Customer> customers = new HashMap<>();

    public int addCustomer(Customer customer) {
        int id = currentId.getAndIncrement();
        customers.put(id, customer);
        return id;
    }

    public Customer findCustomer(int id) {
        if (customers.containsKey(id)) {
            return customers.get(id);
        } else {
            throw new EntityNotFoundException("Customer couldn't be found by id: " + id);
        }
    }

    public int calcValuesSum() {
        int result = 0;
        for (Map.Entry<Integer, Customer> e : customers.entrySet()) {
            result += e.getValue().value;
        }
        return result;
    }

    public Customer getCustomerWithMaxValue() {
        Customer result = null;
        for (Map.Entry<Integer, Customer> e : customers.entrySet()) {
            if (result == null) {
                result = e.getValue();
                continue;
            }
            if (result.value < e.getValue().value) {
                result = e.getValue();
            }
        }
        if (result == null) {
            throw new EntityNotFoundException("There are no customers");
        }
        return result;
    }

    public int size() {
        return customers.size();
    }
}
