package facades;

import entities.Customer;
import entities.ItemType;
import entities.OrderEnt;
import entities.OrderLine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author andreas
 */
public class CustomerFacade {
    EntityManagerFactory emf;
    
    public CustomerFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Customer getCustomer(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }
    
    public OrderEnt getOrder(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderEnt.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getOrderTotalPrice(long id) {
        EntityManager em = getEntityManager();
        try {
            int price = 0;
            List<OrderLine> orderLines = getCustomerOrdersOrderLines(id);
            for(OrderLine ol : orderLines)
                price += ol.getQuantity() * ol.getItemType().getPrice();
            
            return price;
        } finally {
            em.close();
        }
    }
    
    public List<OrderEnt> getCustomerOrders(long customerId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT o FROM OrderEnt o WHERE o.customer = :customer", OrderEnt.class)
                    .setParameter("customer", getCustomer(customerId))
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<OrderLine> getCustomerOrdersOrderLines(long orderId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT ol FROM OrderLine ol WHERE ol.order = :order", OrderLine.class)
                    .setParameter("order", getOrder(orderId))
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Customer> getAllCustomers() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c").getResultList();
        } finally {
            em.close();
        }
    }
    
    public Customer createCustomer(String name, String email) {
        EntityManager em = getEntityManager();
        try {
            Customer c = new Customer(name, email);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }
    
    public OrderEnt createOrder(long customerId, Map<ItemType, Integer> items) {
        EntityManager em = getEntityManager();
        try {
            Customer c = getCustomer(customerId);
            OrderEnt order = new OrderEnt();
            
            List<OrderLine> orderLines = new ArrayList();
            for(Map.Entry<ItemType, Integer> item : items.entrySet()) {
                OrderLine ol = new OrderLine(item.getValue());
                ol.setOrder(order);
                ol.setItemType(item.getKey());
                orderLines.add(ol);
            }
            
            order.setOrderLines(orderLines);
            order.setCustomer(c);
            
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
            return order;
        } finally {
            em.close();
        }
    }
}
