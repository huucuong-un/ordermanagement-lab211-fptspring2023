/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import manager.Management;

/**
 *
 * @author Admin
 */
public class Menu {

    public static void display() {

        Scanner sc = new Scanner(System.in);

        Management m = new Management();

        int choice;
        boolean cont = true;

        try {
            m.loadFromProductFile();
            m.loadFromCustomerFile();
        } catch (IOException ex) {
            System.err.println("Can not load data from products.txt!!!!!!!!!!");
        }

        do {
            System.out.println("=======Order Management=======");
            System.out.println("1. List all Products");
            System.out.println("2. List all Customers");
            System.out.println("3. Search a Customer based on his/her ID");
            System.out.println("4. Add a Customers");
            System.out.println("5. Update a Customers");
            System.out.println("6. List all Orders");
            System.out.println("7. List all Pending Orders");
            System.out.println("8. Add an Order");
            System.out.println("9. Update Order");
            System.out.println("10.Exit");
            System.out.println("===============================");

            System.out.print("Enter your choice: ");

            
            do {
                try {
                    choice = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.err.println("Please enter a number");
                    System.out.print("Enter your choice: ");

                }
            } while (true);

            switch (choice) {

                case 1:
                    boolean contCase1 = true;

                    do {
                        m.displayProducts();

                        System.out.print("\nDo you want to go back to MENU (Y/N)?: ");
                        String s;
                        boolean cond = true;
                        do {
                            s = sc.nextLine();

                            if (s.equals("Y") || s.equals("y")) {
                                cond = false;
                                contCase1 = false;
                                break;
                            } else if (s.equals("N") || s.equals("n")) {
                                contCase1 = true;
                                cond = false;
                            } else {
                                System.out.print("Please enter Y/N: ");
                                cond = true;
                            }
                        } while (cond);

                    } while (contCase1);
                    break;

                case 2:
                    boolean contCase2 = true;

                    do {
                        m.displayCustomers();

                        System.out.print("\nDo you want to go back to MENU (Y/N)?: ");
                        String s;
                        boolean cond = true;
                        do {
                            s = sc.nextLine();

                            if (s.equals("Y") || s.equals("y")) {
                                cond = false;
                                contCase2 = false;
                                break;
                            } else if (s.equals("N") || s.equals("n")) {
                                contCase2 = true;
                                cond = false;
                            } else {
                                System.out.print("Please enter Y/N: ");
                                cond = true;
                            }
                        } while (cond);

                    } while (contCase2);
                    break;

                case 3:
                    boolean contCase3 = true;

                    do {

                        m.searchCustomer();

                        System.out.print("\nDo you want to go back to MENU (Y/N)?: ");
                        String s;
                        boolean cond = true;
                        do {
                            s = sc.nextLine();

                            if (s.equals("Y") || s.equals("y")) {
                                cond = false;
                                contCase3 = false;
                                break;
                            } else if (s.equals("N") || s.equals("n")) {
                                contCase3 = true;
                                cond = false;
                            } else {
                                System.out.print("Please enter Y/N: ");
                                cond = true;
                            }
                        } while (cond);

                    } while (contCase3);
                    break;

                case 4:
                    boolean contCase4 = true;

                    do {

                        try {
                            m.addCustomer();
                        } catch (IOException ex) {
                            System.err.println("Failed!!!");;
                        }

                        System.out.println("");
                        m.displayCustomers();

                        System.out.print("\nDo you want to go back to MENU (Y/N)?: ");
                        String s;
                        boolean cond = true;
                        do {
                            s = sc.nextLine();

                            if (s.equals("Y") || s.equals("y")) {
                                cond = false;
                                contCase4 = false;
                                break;
                            } else if (s.equals("N") || s.equals("n")) {
                                contCase4 = true;
                                cond = false;
                            } else {
                                System.out.print("Please enter Y/N: ");
                                cond = true;
                            }
                        } while (cond);

                    } while (contCase4);
                    break;

                case 5:
                    boolean contCase5 = true;

                    do {
                        m.displayCustomers();
                        try {
                            m.updateCustomer();
                        } catch (IOException ex) {
                            System.err.println("Failed!!!");;
                        }

                        System.out.println("");
                        m.displayCustomers();

                        System.out.print("\nDo you want to go back to MENU (Y/N)?: ");
                        String s;
                        boolean cond = true;
                        do {
                            s = sc.nextLine();

                            if (s.equals("Y") || s.equals("y")) {
                                cond = false;
                                contCase5 = false;
                                break;
                            } else if (s.equals("N") || s.equals("n")) {
                                contCase5 = true;
                                cond = false;
                            } else {
                                System.out.print("Please enter Y/N: ");
                                cond = true;
                            }
                        } while (cond);

                    } while (contCase5);
                    break;

                case 6:
                    boolean contCase6 = true;

                    do {

                        m.displayOrders();

                        System.out.print("\nDo you want to go back to MENU (Y/N)?: ");
                        String s;
                        boolean cond = true;
                        do {
                            s = sc.nextLine();

                            if (s.equals("Y") || s.equals("y")) {
                                cond = false;
                                contCase6 = false;
                                break;
                            } else if (s.equals("N") || s.equals("n")) {
                                contCase6 = true;
                                cond = false;
                            } else {
                                System.out.print("Please enter Y/N: ");
                                cond = true;
                            }
                        } while (cond);

                    } while (contCase6);
                    break;

                case 7:
                    boolean contCase7 = true;

                    do {

                        m.displayPendingOrders();

                        System.out.print("\nDo you want to go back to MENU (Y/N)?: ");
                        String s;
                        boolean cond = true;
                        do {
                            s = sc.nextLine();

                            if (s.equals("Y") || s.equals("y")) {
                                cond = false;
                                contCase7 = false;
                                break;
                            } else if (s.equals("N") || s.equals("n")) {
                                contCase7 = true;
                                cond = false;
                            } else {
                                System.out.print("Please enter Y/N: ");
                                cond = true;
                            }
                        } while (cond);

                    } while (contCase7);
                    break;

                case 8:
                    boolean contCase8 = true;

                    do {

                        m.displayOrders();
                        try {
                            m.addOrder();
                        } catch (IOException ex) {
                            System.err.println("Failed!!!");;
                        }
                        m.displayOrders();

                        System.out.print("\nDo you want to go back to MENU (Y/N)?: ");
                        String s;
                        boolean cond = true;
                        do {
                            s = sc.nextLine();

                            if (s.equals("Y") || s.equals("y")) {
                                cond = false;
                                contCase8 = false;
                                break;
                            } else if (s.equals("N") || s.equals("n")) {
                                contCase8 = true;
                                cond = false;
                            } else {
                                System.out.print("Please enter Y/N: ");
                                cond = true;
                            }
                        } while (cond);

                    } while (contCase8);
                    break;

                case 9:
                    boolean contCase9 = true;

                    do {

                        m.displayOrders();

                        try {
                            m.updateOrds();
                        } catch (IOException ex) {
                            System.err.println("Failed");
                        }
                        m.displayOrders();

                        System.out.print("\nDo you want to go back to MENU (Y/N)?: ");
                        String s;
                        boolean cond = true;
                        do {
                            s = sc.nextLine();

                            if (s.equals("Y") || s.equals("y")) {
                                cond = false;
                                contCase9 = false;
                                break;
                            } else if (s.equals("N") || s.equals("n")) {
                                contCase9 = true;
                                cond = false;
                            } else {
                                System.out.print("Please enter Y/N: ");
                                cond = true;
                            }
                        } while (cond);

                    } while (contCase9);
                    break;

                case 10:
                    cont = false;
                    System.out.println("\nGood bye. See ya!!!!!!!\n");
                    break;

                default:
                    System.out.println("Please enter 1 - 10");

            }

        } while (cont);

    }

}
