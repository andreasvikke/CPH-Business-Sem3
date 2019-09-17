package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author andreas
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    //@OneToOne(cascade = CascadeType.PERSIST)
//    @OneToMany(cascade = CascadeType.PERSIST)
//    @JoinColumn(name="CUSTOMER_ID")
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList();
    
    @ElementCollection
    @CollectionTable(
        name="HOBBIES",
        joinColumns=@JoinColumn(name="CUSTOMER_ID")
    )
    @Column(name="HOBBY")
    private List<String> hobbies = new ArrayList();
    
    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name = "PHONE")
    @Column(name="Description")
    private Map<String, String> phones = new HashMap();

    public Customer() {
    }

    public Customer(String firstName, String lastNAme) {
        this.firstName = firstName;
        this.lastName = lastNAme;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNAme() {
        return lastName;
    }

    public void setLastNAme(String lastName) {
        this.lastName = lastName;
    }
    
    public void addHobby(String s) {
        hobbies.add(s);
    }
    
    public String getHobbies() {
        return String.join(", ", hobbies);
    }
    
    public void addPhone(String phoneNo, String desc) {
        phones.put(phoneNo, desc);
    }
    
    public String getPhoneDesc(String phoneNo) {
        return phones.get(phoneNo);
    }
    
    

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address addresses) {
        this.addresses.add(addresses);
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", addresses=" + addresses + ", hobbies=" + hobbies + ", phones=" + phones + '}';
    }
}
