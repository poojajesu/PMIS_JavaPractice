package com.banking.model;

public class Customer {
    private long customerId;
    private String name;
    private String address;
    private String contact;

    public Customer() {}

    public Customer(long customerId, String name, String address, String contact) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public Customer(String name, String address, String contact) {
        this(0, name, address, contact);
    }

    public long getCustomerId() { return customerId; }
    public void setCustomerId(long customerId) { this.customerId = customerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    @Override
    public String toString() {
        return "Customer{id=" + customerId + ", name='" + name + "', address='" + address
                + "', contact='" + contact + "'}";
    }
}
