package Test;

import entities.Address;
import entities.Customer;
import facades.CustomerFacade;
import javax.persistence.Persistence;

/**
 *
 * @author andreas
 */
public class Tester3 {
    public static void main(String[] args) {
        CustomerFacade cf = new CustomerFacade(Persistence.createEntityManagerFactory("pu"));
        
        Customer c1 = new Customer("Andreas", "Vikke");
        Customer c2 = new Customer("Martin", "Frederiksen");
        
        c1.addHobby("Computer");
        c1.addHobby("3D Printer");
        c2.addHobby("Computer");
        c2.addHobby("Whiskey");
        
        c1.addPhone("28438851", "Home");
        c1.addPhone("28438852", "Phone");
        c2.addPhone("12345678", "Home");
        c2.addPhone("12345679", "Phone");
        
        Address a1 = new Address("Marievej 40", "Skovlunde");
        Address a2 = new Address("Magleparken 53", "Ballerup");
        Address a3 = new Address("Vejnavn1", "Nærum");

        c1.addAddress(a1);
        c1.addAddress(a2);
        c2.addAddress(a3);
        c2.addAddress(a2);
        
        cf.addCustomer(c1);
        cf.addCustomer(c2);
        
        for(Customer c : cf.getCustomers())
            System.out.println(c);
        
        c1.setLastNAme("Test");
        cf.editCustomer(c1);
        System.out.println(cf.getCustomer(c1.getId()));
        
        cf.deleteCustomer(c2.getId());
        System.out.println(cf.getCustomer(c2.getId()));
    }
}
