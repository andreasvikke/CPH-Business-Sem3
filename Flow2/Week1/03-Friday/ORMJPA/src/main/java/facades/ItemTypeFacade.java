package facades;

import entities.ItemType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author andreas
 */
public class ItemTypeFacade {
    EntityManagerFactory emf;
    
    public ItemTypeFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ItemType getItemType(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItemType.class, id);
        } finally {
            em.close();
        }
    }
    
    public ItemType createCustomer(String name, String description, int price) {
        EntityManager em = getEntityManager();
        try {
            ItemType c = new ItemType(name, description, price);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }
}
