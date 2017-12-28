import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ProductID;
    private String ProductName;
    private Integer UnitsOnStock;

    @ManyToOne
    @JoinColumn
    private Category Category;

    @ManyToOne
    @JoinColumn
    private Supplier supplier;

    @ManyToMany
    @JoinTable(name = "Product_TTransaction",
                joinColumns = @JoinColumn(name = "Product_ID"),
                inverseJoinColumns = @JoinColumn(name = "TTransaction_ID"))
    private List<TTransaction> transactions = new LinkedList<>();

    public Product(){

    }

    public Product(String productName, Integer unitsOnStock) {
        ProductName = productName;
        UnitsOnStock = unitsOnStock;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.getSupplyingProducts().add(this);
    }

    public void setCategory(Category category){
        Category = category;
        Category.getProducts().add(this);
    }

    public void addTransaction(TTransaction transaction){
        transactions.add(transaction);
        transaction.getProducts().add(this);
        transaction.addUnit();
    }

    public List<TTransaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return ProductName + " | " + UnitsOnStock + " | " + Category.getName();
    }
}
