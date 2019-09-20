package test;

import entities.Customer;
import entities.ItemType;
import entities.OrderEnt;
import entities.OrderLine;
import facades.CustomerFacade;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andreas
 */
public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade cf = new CustomerFacade(emf);
        
        Map<ItemType, Integer> itemTypes = new HashMap();
        itemTypes.put(new ItemType("Test", "Test Item", 100), 2);
        itemTypes.put(new ItemType("Test 2", "Test Item2", 500), 9);
        
        cf.createCustomer("Andreas Vikke", "vikkedesign@gmail.com");
        cf.createOrder(1, itemTypes);
        
        Customer c = cf.getCustomer(1);
        System.out.println(c);
        for(OrderEnt order : cf.getCustomerOrders(c.getId()))
            System.out.println(order);
        
        for(OrderLine order : cf.getCustomerOrdersOrderLines(1))
            System.out.println(order);
        
        System.out.println("Total price of order 1: " + cf.getOrderTotalPrice(1));
    }
}
