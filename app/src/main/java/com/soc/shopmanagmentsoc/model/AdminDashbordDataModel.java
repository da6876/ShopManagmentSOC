package com.soc.shopmanagmentsoc.model;

import java.io.Serializable;

/**
 * Created by user on 6/30/2018.
 */

public class AdminDashbordDataModel implements Serializable {
    private String complain;
    private String status;
    private String customerNumber;

    public AdminDashbordDataModel() {
    }

    public AdminDashbordDataModel(String complain, String status, String customerNumber) {
        this.complain = complain;
        this.status = status;
        this.customerNumber = customerNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
