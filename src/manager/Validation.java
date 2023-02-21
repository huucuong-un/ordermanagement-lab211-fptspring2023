/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.List;
import java.util.Scanner;
import myobjects.Customers;
import myobjects.Orders;
import myobjects.Products;

/**
 *
 * @author Admin
 */
public class Validation {
    //input value: int, double, id

    private static Scanner sc = new Scanner(System.in);

    public static int getInt(String msg, int min, int max) {
        int number;
        while (true) {
            try {
                System.out.print(msg);
                number = Integer.parseInt(sc.nextLine());

                if (min <= number) {
                    break;
                }

                throw new NumberFormatException();

            } catch (NumberFormatException e) {
                System.err.println("Enter integer number from " + min);
                System.out.println("");
            }
        }

        return number;
    }

    public static double getDouble(String msg, double min, double max) {
        double number;
        while (true) {
            try {
                System.out.print(msg);
                number = Double.parseDouble(sc.nextLine());

                if (min <= number && number <= max) {
                    break;
                }

                throw new NumberFormatException();

            } catch (NumberFormatException e) {
                System.err.println("Enter real number from " + min);
                System.out.println("");
            }
        }

        return number;
    }

    public static boolean getOrderStatus(String msg, String err) {
        //mode 1: input
        // mode 3: update
        String status;

        while (true) {
            System.out.print(msg);
            status = sc.nextLine();

            if (status.equals("") ) {
                return false;
               
            } else if (status.equals("true")) {
                return true;
            } else if (status.equals("false")) {
                return false;
            } else {
                System.err.println(err);
            }
        }
    }

    public static String getString(String msg, int mode) {
        //mode = 1: input;
        //       2: update, can get ""
        String s;
        while (true) {
            try {
                System.out.print(msg);
                s = sc.nextLine();

                if (((s.equals("")) || (s.equals(null))) && mode == 1) {
                    throw new Exception();
                } else if (s.equals("") && mode == 2) {
                    return s;
                } else {
                    return s;
                }

            } catch (Exception e) {
                System.err.println("Can not be null || empty");
                System.out.println("");

            }
        }

    }

    public static boolean idCustomerExist(String id, List<Customers> cList) { //CHECK Customer ID EXIST
        for (Customers x : cList) {
            if (x.getCustomerID().equals(id)) {
                return true;
            }
        }

        return false;
    }

    public static String getCustomerID(String msg, int mode, List<Customers> cList, String error) {
        System.out.println("");
        for (Customers customers : cList) {
            System.out.print(customers);
        }
        System.out.println("");

        String s;
        while (true) {
            System.out.print(msg);
            s = sc.nextLine();

            // mode:
            // 1. input ~ id not exist
            // 2. add to order ~ id must existed
            // 3. update ~ can get ""
            if (s.matches("C[0-9]{3}")) {
                if ((mode == 1 && !idCustomerExist(s, cList)) || (mode == 2 && idCustomerExist(s, cList))) {
                    break;
                }

                if (mode == 1 && idCustomerExist(s, cList)) {
                    System.err.println("Existed ID, please input again");

                }

                if ((mode == 2 || mode == 3) && !idCustomerExist(s, cList)) {
                    System.err.println("Does not exist!!!");

                }

                if ((mode == 2 || mode == 3) && idCustomerExist(s, cList)) {
                    break;
                }
            } else if (s.equals("") && mode == 3) {
                break;
            } else {
                System.err.println(error);
            }
        }
        return s;
    }

    public static String getPhoneNumber(String msg, String error, List<Customers> cList, int mode) { //VALID PHONENUMBER
        //mode 1: add
        //mode 2: update                     has ""
        String phoneNumber;

        while (true) {
            System.out.print(msg);
            phoneNumber = sc.nextLine();

            if (!phoneExist(phoneNumber, cList) && (phoneNumber.matches("^[0-9]{10,12}$")) && (mode == 1 || mode == 2)) {
                break;
            } else if (phoneNumber.equals("") && mode == 2) {
                break;
            } else if (phoneExist(phoneNumber, cList)) {
                System.err.println("Existed phone number, please enter again");
            } else {
                System.err.println(error);
            }
        }

        return phoneNumber;

    }

    private static boolean phoneExist(String phoneNumber, List<Customers> cList) { //CHECK PHONE EXIST
        for (Customers x : cList) {
            if (x.getCustomerPhone().equals(phoneNumber)) {
                return true;
            }
        }

        return false;
    }

    public static String cName(String id, List<Customers> cList, List<Orders> oList) {

        String name;
        for (Customers c : cList) {
            if (c.getCustomerID().equals(id)) {
                name = c.getCustomerName();
                return name;
            }
        }
        return null;
    }

    public static String getOrderID(String msg, int mode, List<Orders> oList, String error) {
        
        System.out.println("");
        String s;
        while (true) {
            System.out.print(msg);
            s = sc.nextLine();

            // mode:
            // 1. input ~ id not exist
            // 2. delete ~ id must existed
            // 3. update ~ can get ""
            if (s.matches("D[0-9]{3}")) {
                if ((mode == 1 && !idOrderExist(s, oList)) || mode == 2 && idOrderExist(s, oList)) {
                    break;
                }

                if (mode == 1 && idOrderExist(s, oList)) {
                    System.err.println("Existed ID, please input again");

                }

                if ((mode == 2 || mode == 3)&& !idOrderExist(s, oList)) {
                    System.err.println("Does not exist!!!");

                }

                if ((mode == 2 || mode == 3) && idOrderExist(s, oList)) {
                    break;
                }
            } else if (s.equals("") && mode == 3) {
                break;
            } else {
                System.err.println(error);
            }
        }
        return s;
    }

    public static boolean idOrderExist(String id, List<Orders> oList) { //CHECK Customer ID EXIST
        for (Orders x : oList) {
            if (x.getOrderID().equals(id)) {
                return true;
            }
        }

        return false;
    }

    public static boolean idProductExist(String id, List<Products> pList) { //CHECK Customer ID EXIST
        for (Products x : pList) {
            if (x.getProductID().equals(id)) {
                return true;
            }
        }

        return false;
    }

    public static String getProductID(String msg, int mode, List<Products> pList, String error) {
        System.out.println("");
        for (Products products : pList) {
            System.out.print(products);
        }
        System.out.println("");

        String s;
        while (true) {
            System.out.print(msg);
            s = sc.nextLine();

            // mode:
            // 1. input to order ~ id must exist
            // 2. delete ~ id must existed
            // 3. update ~ can get ""
            if (s.matches("P[0-9]{3}")) {
                if ((mode == 1 && idProductExist(s, pList)) || mode == 2 && idProductExist(s, pList)) {
                    break;
                }

                if (mode == 1 && !idProductExist(s, pList)) {
                    System.err.println("ID not existed, please input again");

                }

                if ((mode == 2 || mode == 3)&& !idProductExist(s, pList)) {
                    System.err.println("Does not exist!!!");

                }
//
                if ((mode == 2 || mode == 3) && idProductExist(s, pList)) {
                    break;
                }
            } else if (s.equals("") && mode == 3) {
                break;
            } else {
                System.err.println(error);
            }
        }
        return s;
    }

    public static String getOrderDate(String msg, int mode) {
        String s;

        while (true) {
            System.out.print(msg);
            s = sc.nextLine();

            // mode:
            // 1. input to order ~ id must exist
            // 2. update ~ can include""
            if(s.equals("") && mode == 3) {
                return s;
            }   
            
            if (s.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
                if (mode == 1  || mode == 3) {
                    return s;
                }

            } else if (s.matches("") && mode == 1) {
                System.err.println("The order’s Date cannot be blank!!!");

            } else {
                System.err.println("Please input dd/mm/yyyy (with first is DAY, second is MONTH, and last is YEAR)");
            }

        }
      
    }
}
