package org.customerPortal.dto;

import org.customerPortal.database.Customer;

import java.util.List;

public class MatchResponse {
    List<Customer> onlyInListA;
    List<Customer> onlyInListB;
    List<Customer> inBothList;

    public List<Customer> getOnlyInListA() {
        return onlyInListA;
    }

    public void setOnlyInListA(List<Customer> onlyInListA) {
        this.onlyInListA = onlyInListA;
    }

    public List<Customer> getOnlyInListB() {
        return onlyInListB;
    }

    public void setOnlyInListB(List<Customer> onlyInListB) {
        this.onlyInListB = onlyInListB;
    }

    public List<Customer> getInBothList() {
        return inBothList;
    }

    public void setInBothList(List<Customer> inBothList) {
        this.inBothList = inBothList;
    }
}
