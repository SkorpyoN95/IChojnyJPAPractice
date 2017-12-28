import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class TTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TransactionNumber;
    private int Quantity = 0;

    @ManyToMany
    private List<Product> Products = new LinkedList<>();

    public TTransaction(){}

    public void addProduct(Product product){
        Products.add(product);
        product.getTransactions().add(this);
        addUnit();
    }

    public void addUnit(){
        Quantity++;
    }

    public List<Product> getProducts() {
        return Products;
    }

    @Override
    public String toString() {
        return TransactionNumber + " | " + Quantity;
    }
}
