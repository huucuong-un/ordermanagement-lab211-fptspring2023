/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myobjects.Customers;
import myobjects.Orders;
import myobjects.Products;

/**
 *
 * @author Admin
 */
public class Management {

    private Scanner sc = new Scanner(System.in);
    private List<Products> pList;
    private List<Customers> cList;
    private List<Orders> oList;

    public Management() {
        pList = new ArrayList<>();
        cList = new ArrayList<>();
        oList = new ArrayList<>();

    }

    public void loadFromProductFile() throws IOException, FileNotFoundException {
        File f = new File("products.txt");

        FileReader fr = new FileReader(f);

        BufferedReader br = new BufferedReader(fr);

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }

            String[] data = line.split("[,]");
            String productID = data[0].trim(); //format de doc
            String productName = data[1].trim();
            String unit = data[2].trim();
            String origin = data[3].trim();
            double price = Double.parseDouble(data[4].trim());

            pList.add(new Products(productID, productName, unit, origin, price));

        }

        fr.close();
        br.close();
    }

    public void loadFromCustomerFile() throws IOException, FileNotFoundException {
        File f = new File("customers.txt");

        FileReader fr = new FileReader(f);

        BufferedReader br = new BufferedReader(fr);

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }

            String[] data = line.split("[,]");
            String customerID = data[0].trim(); //format de doc
            String customerName = data[1].trim();
            String customerAddress = data[2].trim();
            String customerPhone = data[3].trim();

            cList.add(new Customers(customerID, customerName, customerAddress, customerPhone));

        }

        fr.close();
        br.close();
    }

    public void displayProducts() {
        for (Products products : pList) {
            System.out.printf("%s,%s,%s,%s,%.3f\n", products.getProductID(), products.getProductName(), products.getUnit(), products.getOrigin(), products.getPrice());
        }
    }

    public void displayCustomers() {
        cList.removeAll(cList);
        try {
            this.loadFromCustomerFile();
        } catch (IOException ex) {
            System.err.println("Failed!!!!!");
        }

        for (Customers customers : cList) {
            System.out.printf("%s,%s,%s,%s\n", customers.getCustomerID(), customers.getCustomerName(), customers.getCustomerAddress(), customers.getCustomerPhone());
        }
    }

    public void searchCustomer() {
        String customerIDSearch;

        this.displayCustomers();

        System.out.print("\n\nEnter ID of customer you want to search: ");
        customerIDSearch = sc.nextLine();
        System.out.println("");

        for (Customers c : cList) {
            if (customerIDSearch.equals(c.getCustomerID())) {
                System.out.println(c);
                return;
            }
        }

        if (customerIDSearch.equals("")) {
            for (Customers c : cList) {
                System.out.print(c);
            }
            return;
        }

        System.err.println("This customer does not exist!!!!!");

    }

    public void addCustomer() throws IOException {

        String id, name, address, phone;

        id = Validation.getCustomerID("Enter Customer ID: ", 1, cList, "ID field is not allowed to duplicate and has format Cxxx with x is digit");
        name = Validation.getString("Enter name: ", 1);
        address = Validation.getString("Enter address: ", 1);
        phone = Validation.getPhoneNumber("Enter phone number: ", "Invalid phone number\nPhone number must be in rang 0 -9 and has 10-12 length number and not be Duplicated", cList, 1);

        cList.add(new Customers(id, name, address, phone));

        this.saveToCustomerFile();
        cList.removeAll(cList);

    }

    public void saveToCustomerFile() {
        try {
            File f = new File("customers.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Customers x : cList) {
                bw.write(x.toString());
            }

            bw.close();
            fw.close();
            System.out.println("Succesfully!!!!!!!!!!");

        } catch (IOException e) {

            System.err.println("\n\nFailed!!!!!!!\n");
        }

    }

    public void updateCustomer() throws IOException {

        String customerUpdate;
        String customerNameUpdate;
        String customerAddressUpdate;
        String customerPhoneUpdate;

        int count = 0;

        do {
            System.out.print("\nEnter Customer ID to update: ");
            customerUpdate = sc.nextLine();

            for (Customers c : cList) {
                if (customerUpdate.equals(c.getCustomerID())) {
                    System.out.println(c + "\n");

                    customerNameUpdate = Validation.getString("Enter new name: ", 2);
                    if (customerNameUpdate.equals("")) {
                        c.setCustomerName(c.getCustomerName());
                    } else {
                        c.setCustomerName(customerNameUpdate);
                    }

                    customerAddressUpdate = Validation.getString("Enter new address: ", 2);
                    if (customerAddressUpdate.equals("")) {
                        c.setCustomerAddress(c.getCustomerAddress());
                    } else {
                        c.setCustomerAddress(customerAddressUpdate);
                    }

                    customerPhoneUpdate = Validation.getPhoneNumber("Enter new phone number: ", "Invalid phone number", cList, 2);
                    if (customerPhoneUpdate.equals("")) {
                        c.setCustomerPhone(c.getCustomerPhone());
                    } else {
                        c.setCustomerPhone(customerPhoneUpdate);
                    }

                    count++;

                    this.saveToCustomerFile();
                }
            }

            if (count == 0) {
                System.err.println("Customer does not exist");
            } else {
                break;
            }
        } while (true);

        cList.removeAll(cList);
        this.loadFromCustomerFile();

    }

    public void loadFromOrderFile() throws IOException, FileNotFoundException {
        File f = new File("orders.txt");

        FileReader fr = new FileReader(f);

        BufferedReader br = new BufferedReader(fr);

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }

            String[] data = line.split("[,]");
            String orderID = data[0].trim(); //format de doc
            String customerID = data[1].trim();
            String productID = data[2].trim();
            int orderQuantity = Integer.parseInt(data[3].trim());
            String orderDate = data[4].trim();
            boolean status = Boolean.parseBoolean(data[5].trim());

            oList.add(new Orders(orderID, customerID, productID, orderQuantity, orderDate, status));

        }

        fr.close();
        br.close();
    }

    public void saveToOrderFile() {
        try {
            File f = new File("orders.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Orders x : oList) {
                bw.write(x.toString());
            }

            bw.close();
            fw.close();
            System.out.println("Succesfully!!!!!!!!!!");

        } catch (IOException e) {

            System.err.println("\n\nFailed!!!!!!!\n");
        }

    }

    public void displayOrders() {
        oList.removeAll(oList);

        try {
            this.loadFromOrderFile();
        } catch (IOException e) {
            System.err.println("Failed!!!!!");
        }

        for (int i = 0; i < oList.size() - 1; i++) {
            for (int j = i + 1; j < oList.size(); j++) {
                if (Validation.cName(oList.get(i).getCustomerID(), cList, oList).charAt(0) > Validation.cName(oList.get(j).getCustomerID(), cList, oList).charAt(0)) {

                    Collections.swap(oList, i, j);

                }
            }
        }

        for (Orders o : oList) {
            System.out.printf("%s,%s,%s,%s,%d,%s,%b\n", o.getOrderID(), o.getCustomerID(), Validation.cName(o.getCustomerID(), cList, oList), o.getProductID(), o.getOrderQuantity(), o.getOrderDate(), o.getStatus());
        }
    }

    public void displayPendingOrders() {
        oList.removeAll(oList);

        try {
            this.loadFromOrderFile();
        } catch (IOException e) {
            System.err.println("Failed!!!!!");
        }

        for (int i = 0; i < oList.size() - 1; i++) {
            for (int j = i + 1; j < oList.size(); j++) {
                if (Validation.cName(oList.get(i).getCustomerID(), cList, oList).charAt(0) > Validation.cName(oList.get(j).getCustomerID(), cList, oList).charAt(0)) {

                    Collections.swap(oList, i, j);

                }
            }
        }

        for (Orders o : oList) {
            if (o.getStatus() == false) {
                System.out.printf("%s,%s,%s,%s,%d,%s,%b\n", o.getOrderID(), o.getCustomerID(), Validation.cName(o.getCustomerID(), cList, oList), o.getProductID(), o.getOrderQuantity(), o.getOrderDate(), o.getStatus());
            }
        }
    }

    public void addOrder() throws IOException {
        String orderID, customerID, productID, orderDate;
        boolean status;
        int quantity;

        orderID = Validation.getOrderID("Enter Order ID: ", 1, oList, "Invalid type");
        customerID = Validation.getCustomerID("Enter Customer ID: ", 2, cList, "Invalid type");
        productID = Validation.getProductID("Enter Product ID: ", 1, pList, "Invalid type");
        quantity = Validation.getInt("Enter quantity: ", 0, 99999999);
        orderDate = Validation.getOrderDate("Enter date: ", 1);
        status = Validation.getOrderStatus("Enter status: ", "Enter true or false");

        oList.add(new Orders(orderID, customerID, productID, quantity, orderDate, status));

        this.saveToOrderFile();;
        oList.removeAll(oList);

    }

    public void updateOrder() throws IOException {

        String orderUpdate;
        String customerIDUpdate;
        String productIDUpdate;

        String orderQuantityUpdate;
        String orderDateUpdate;
        Boolean orderStatusUpdate;

        int count = 0;

        do {
            System.out.print("\nEnter Order ID to update: ");
            orderUpdate = sc.nextLine();

            for (Orders o : oList) {
                if (orderUpdate.equals(o.getOrderID())) {
                    System.out.println(o + "\n");

                    customerIDUpdate = Validation.getCustomerID("Enter new customer ID to update: ", 3, cList, "Invalid");
                    if (customerIDUpdate.equals("")) {
                        o.setCustomerID(o.getCustomerID());
                    } else {
                        o.setCustomerID(customerIDUpdate);

                    }

                    productIDUpdate = Validation.getProductID("Enter new product ID to update:", 3, pList, "Invalid");
                    if (productIDUpdate.equals("")) {
                        o.setProductID(o.getProductID());
                    } else {
                        o.setProductID(productIDUpdate);
                    }

                    do {
                        System.out.print("Enter new quantity to update: ");
                        orderQuantityUpdate = sc.nextLine();
                        if (orderQuantityUpdate.equals("")) {
                            o.setOrderQuantity(o.getOrderQuantity());
                            break;
                        } else if (orderQuantityUpdate.matches("^([0-9]){1,15}$")) {
                            o.setOrderQuantity(Integer.parseInt(orderQuantityUpdate));
                            break;
                        } else {
                            System.err.println("Invalid quantity");
                        }
                    } while (true);

                    orderDateUpdate = Validation.getOrderDate("Enter new date to update: ", 3);
                    if (orderDateUpdate.equals("")) {
                        o.setOrderDate(o.getOrderDate());
                    } else {
                        o.setOrderDate(orderDateUpdate);
                    }

                    orderStatusUpdate = Validation.getOrderStatus("Enter new status (true || false):  ", "Invalid");
                    if (orderStatusUpdate.equals("")) {
                        o.setStatus(o.getStatus());
                    } else {
                        o.setStatus(orderStatusUpdate);
                    }

                    count++;

                    this.saveToOrderFile();
                }
            }

            if (count == 0) {
                System.err.println("Customer does not exist");
            } else {
                break;
            }
        } while (true);

        oList.removeAll(oList);
        this.loadFromOrderFile();

    }

    public void deleteOrder() throws IOException {

        String id;

        id = Validation.getOrderID("ID of order you  want to remove: ", 2, oList, "Order Id does not exist, Deletete failed");
        System.out.println("");
        for (Orders o : oList) {
            if (o.getOrderID().equals(id)) {
                System.out.println("");
                System.out.println(o);
                System.out.println("");
            }
        }

        do {
            System.out.print("Do you really want to delete this order? (yes | no): ");
            String choice = sc.nextLine();
            if (choice.equals("y") || choice.equals("yes")) {
                for (Orders x : oList) {

                    if (x.getOrderID().equals(id)) {

                        oList.remove(x);
                        this.saveToOrderFile();

                        oList.removeAll(oList);
                        this.loadFromOrderFile();

                        return;

                    }
                }
            } else if (choice.equals("n") || choice.equals("no")) {
                break;
            } else {
                System.err.println("Enter yes or no");
            }

        } while (true);

        oList.removeAll(oList);
        this.loadFromOrderFile();

    }

    public void updateOrds() throws IOException {
        System.out.println("=========Update==========");
        System.out.println("1. Update Order information");
        System.out.println("");
        System.out.println("2. Delete Order");

        int choice;

        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    this.updateOrder();
                    break;
                } else if (choice == 2) {
                    this.deleteOrder();
                    break;
                } else {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException e) {
                System.out.println("Please choose 1 or 2");
            }

        } while (true);

    }

}
