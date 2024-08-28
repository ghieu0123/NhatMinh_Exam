package com.samsung.minh;

import com.samsung.minh.controller.CustomerController;
import com.samsung.minh.repositories.Customer;
import com.samsung.minh.services.CustomerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        customer.setName("nql");
        customer.setCustomerNumber("123456");
        customer.setEmail("nql4901@gmail.com");

        when(customerService.addCustomer(customer)).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.addCustomer(customer);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getName(), "nql");
        verify(customerService, times(1)).addCustomer(customer);
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setName("nql");
        customer1.setCustomerNumber("123456");
        customer1.setEmail("nql4901@gmail.com");

        Customer customer2 = new Customer();
        customer2.setName("bruno");
        customer2.setCustomerNumber("123456");
        customer2.setEmail("bruno@gmail.com");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().size(), 2);
        verify(customerService, times(1)).getAllCustomers();
    }
}
