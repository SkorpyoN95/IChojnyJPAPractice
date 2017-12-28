import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int CategoryID;
    private String name;

    @OneToMany(mappedBy = "Category")
    private List<Product> Products = new LinkedList<>();

    public Category(){

    }

    public Category(String name){
        this.name = name;
    }

    public void addProduct(Product product){
        Products.add(product);
        product.setCategory(this);
    }

    public List<Product> getProducts() {
        return Products;
    }

    public String getName() {
        return name;
    }
}
