package org.acme.dto;

public class CustomerSearchRequest {

    String firstName;
    String city;
    String state;

    CustomerSearchRequest() {
    }

    CustomerSearchRequest(String name, String city, String state) {
        this.city = city;
        this.state = state;
        this.firstName = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
