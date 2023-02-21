/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myobjects;

import manager.Validation;

/**
 *
 * @author Admin
 */
public class Orders {
    private String orderID;
    private String customerID;
    private String productID;
    private int orderQuantity;
    private String orderDate;
    private boolean status;

    public Orders() {
    }

    
    
    public Orders(String orderID, String customerID, String productID, int orderQuantity, String orderDate, boolean status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%d,%s,%b\n", orderID, customerID, productID, orderQuantity, orderDate, status);

    }
    
    
    
    
}
