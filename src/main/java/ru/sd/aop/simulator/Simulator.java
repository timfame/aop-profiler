package ru.sd.aop.simulator;

import ru.sd.aop.dao.EntityNotFoundException;
import ru.sd.aop.domain.Customer;
import ru.sd.aop.domain.CustomerManager;

import java.util.Random;

public class Simulator {
    private final Random random = new Random();
    private final CustomerManager customerManager;

    public Simulator(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public void simulate(int cnt) {
        customerManager.addCustomer(new Customer("name#" + 0));
        for (int i = 1; i < cnt; i++) {
            switch (random.nextInt(4)) {
                case 0:
                    customerManager.addCustomer(new Customer("name#" + i));
                    break;
                case 1:
                    int size = customerManager.size();
                    try {
                        Customer customer = customerManager.findCustomer(random.nextInt(size) + 1);
                    } catch (EntityNotFoundException e) {
                        System.err.println("Find customer failed: " + e.getMessage());
                    }
                    break;
                case 2:
                    int sum = customerManager.calcValuesSum();
                    break;
                case 3:
                    Customer customer = customerManager.getCustomerWithMaxValue();
                    break;
            }
        }
    }
}
