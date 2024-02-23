package org.customerPortal;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.customerPortal.database.Customer;
import org.customerPortal.dto.Address;
import org.customerPortal.dto.MatchResponse;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class GetMatchingAndNonMatchingCustomerTest {

    @Inject
    CustomerSearchManager customerSearchManager;

    public static List<Customer> createListA() {
        List<Customer> listA = new ArrayList<>();


        // Non-matching Customer 1
        Customer nonMatchingCustomer1 = new Customer();
        nonMatchingCustomer1.firstName = "Bob";
        nonMatchingCustomer1.lastName = "Johnson";
        nonMatchingCustomer1.age = 35;
        nonMatchingCustomer1.spendingLimit = 700.0;
        nonMatchingCustomer1.mobileNumber = "555-9876";
        nonMatchingCustomer1.addresses = createAddresses("789 Pine St", "CityC", "StateC", "ZipC");
        nonMatchingCustomer1.setCustomerId(new ObjectId());
        listA.add(nonMatchingCustomer1);

        // Non-matching Customer 2
        Customer nonMatchingCustomer2 = new Customer();
        nonMatchingCustomer2.firstName = "Eve";
        nonMatchingCustomer2.lastName = "Brown";
        nonMatchingCustomer2.age = 25;
        nonMatchingCustomer2.spendingLimit = 800.0;
        nonMatchingCustomer2.mobileNumber = "555-4321";
        nonMatchingCustomer2.addresses = createAddresses("987 Elm St", "CityD", "StateD", "ZipD");
        nonMatchingCustomer2.setCustomerId(new ObjectId());
        listA.add(nonMatchingCustomer2);

        return listA;
    }

    public static List<Customer> createListB() {
        List<Customer> listB = new ArrayList<>();

        // Non-matching Customer 3
        Customer nonMatchingCustomer3 = new Customer();
        nonMatchingCustomer3.firstName = "Charlie";
        nonMatchingCustomer3.lastName = "Williams";
        nonMatchingCustomer3.age = 32;
        nonMatchingCustomer3.spendingLimit = 750.0;
        nonMatchingCustomer3.mobileNumber = "555-1111";
        nonMatchingCustomer3.addresses = createAddresses("111 Cedar St", "CityE", "StateE", "ZipE");
        nonMatchingCustomer3.setCustomerId(new ObjectId());
        listB.add(nonMatchingCustomer3);

        // Non-matching Customer 4
        Customer nonMatchingCustomer4 = new Customer();
        nonMatchingCustomer4.firstName = "David";
        nonMatchingCustomer4.lastName = "Miller";
        nonMatchingCustomer4.age = 27;
        nonMatchingCustomer4.spendingLimit = 850.0;
        nonMatchingCustomer4.mobileNumber = "555-2222";
        nonMatchingCustomer4.addresses = createAddresses("222 Birch St", "CityF", "StateF", "ZipF");
        nonMatchingCustomer4.setCustomerId(new ObjectId());
        listB.add(nonMatchingCustomer4);

        return listB;
    }

    private static List<Address> createAddresses(String street, String city, String state, String zipCode) {
        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        address.city = city;
        address.addressType = "HOME";
        address.address1 = "";
        address.address2 = "";
        address.state = state;
        address.zipcode = zipCode;
        addresses.add(address);
        return addresses;
    }

    @Test
    public void testCustomerSets() {
        // Populate listA and listB with some customers
        List<Customer> customerListA = createListA();
        List<Customer> customerListB = createListB();
        List<Customer> commonCustomers = createCommonCustomer();
        customerListB.addAll(commonCustomers);
        customerListA.addAll(commonCustomers);
        MatchCustomerRequests requests = new MatchCustomerRequests();

        requests.setCustomersListA(customerListA);
        requests.setCustomersListB(customerListB);
        Response response = customerSearchManager.matchCustomers(requests);
        MatchResponse matchResponse = (MatchResponse) response.getEntity();

        assertEquals(matchResponse.getInBothList().size(), 2);

    }

    private List<Customer> createCommonCustomer() {
        List<Customer> common = new ArrayList<>();
        Customer commonCustomer1 = new Customer();
        commonCustomer1.firstName = "John";
        commonCustomer1.lastName = "Doe";
        commonCustomer1.age = 30;
        commonCustomer1.spendingLimit = 500.0;
        commonCustomer1.mobileNumber = "555-1234";
        commonCustomer1.addresses = createAddresses("123 Main St", "CityA", "StateA", "ZipA");
        commonCustomer1.setCustomerId(new ObjectId());
        common.add(commonCustomer1);

        // Common Customer 2
        Customer commonCustomer2 = new Customer();
        commonCustomer2.firstName = "Alice";
        commonCustomer2.lastName = "Smith";
        commonCustomer2.age = 28;
        commonCustomer2.spendingLimit = 600.0;
        commonCustomer2.mobileNumber = "555-5678";
        commonCustomer2.addresses = createAddresses("456 Oak St", "CityB", "StateB", "ZipB");
        commonCustomer1.setCustomerId(new ObjectId());
        common.add(commonCustomer2);
        common.add(commonCustomer1);
        return common;
    }
}