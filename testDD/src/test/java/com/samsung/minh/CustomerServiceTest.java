package com.samsung.minh;

import com.samsung.minh.repositories.Customer;
import com.samsung.minh.repositories.CustomerRepository;
import com.samsung.minh.services.CustomerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        customer.setName("dnm");
        customer.setCustomerNumber("123456");
        customer.setEmail("dnm@gmail.com");

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.addCustomer(customer);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(), "dnm");
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setName("dnm");
        customer1.setCustomerNumber("123456");
        customer1.setEmail("dnm@gmail.com");

        Customer customer2 = new Customer();
        customer2.setName("bruno");
        customer2.setCustomerNumber("123456");
        customer2.setEmail("bruno@gmail.com");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll())
                .thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        Assert.assertEquals(result.size(), 2);
        verify(customerRepository, times(1))
                .findAll();
    }
}
