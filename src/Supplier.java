import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int SupplierID;
    private String CompanyName;
    private String Street;
    private String City;

    @OneToMany(mappedBy = "supplier")
    private Set<Product> SupplyingProducts = new HashSet<>();

    public Supplier(){

    }

    public Supplier(String companyName, String street, String city) {
        CompanyName = companyName;
        Street = street;
        City = city;
    }

    public void addSupplyingProduct(Product product){
        SupplyingProducts.add(product);
        product.setSupplier(this);
    }

    public Set<Product> getSupplyingProducts(){
        return SupplyingProducts;
    }

    @Override
    public String toString(){
        return CompanyName + " | " + Street + " | " + City;
    }
}
