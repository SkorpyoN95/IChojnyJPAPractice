import javax.persistence.Entity;

@Entity
public class Customer extends Company {
    private Integer discount;

    public Customer(){}
    public Customer(String companyName, String street, String city, String zipCode, Integer discount){
        super(companyName, street, city, zipCode);
        this.discount = discount;
    }
}
