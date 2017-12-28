import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supplier extends Company {
    private String bankAccountNumber;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Set<Product> SupplyingProducts = new HashSet<>();

    public Supplier(){

    }

    public Supplier(String companyName, String street, String city, String zipCode, String account) {
        super(companyName, street, city, zipCode);
        bankAccountNumber = account;
    }

    public void addSupplyingProduct(Product product){
        SupplyingProducts.add(product);
        product.setSupplier(this);
    }

    public Set<Product> getSupplyingProducts(){
        return SupplyingProducts;
    }
}
