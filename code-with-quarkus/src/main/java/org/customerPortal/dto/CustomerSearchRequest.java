package org.customerPortal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerSearchRequest {
    @JsonProperty("firstName")
    String firstName;
    @JsonProperty("city")
    String city;
    @JsonProperty("state")
    String state;

    public CustomerSearchRequest() {}

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
