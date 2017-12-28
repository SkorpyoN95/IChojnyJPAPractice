import javax.persistence.*;
import java.util.List;

public class Main2 {

    public static void main(final String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Category category = new Category("Alcohol"),
                category2 = new Category("Bakery");
        em.persist(category);
        em.persist(category2);
        Product product = new Product("Beer", 10),
                product2 = new Product("Vodka", 5),
                product3 = new Product("Bread", 15),
                product4 = new Product("Bun", 20);
        category.addProduct(product);
        category.addProduct(product2);
        category2.addProduct(product3);
        category2.addProduct(product4);
        Supplier supplier = new Supplier("Marek", "Krakowska", "Warszawa", "00-001", "1234567890"),
                supplier2 = new Supplier("Darek","Lwowska", "Tarnów", "33-100", "0987654321"),
                supplier3 = new Supplier("Jarek", "Reymonta", "Kraków", "02-456", "1357924680"),
                supplier4 = new Supplier("Czarek", "Pomorska", "Gdynia", "88-000", "2468013579");
        em.persist(supplier);
        em.persist(supplier2);
        em.persist(supplier3);
        em.persist(supplier4);
        supplier.addSupplyingProduct(product);
        supplier.addSupplyingProduct(product2);
        supplier2.addSupplyingProduct(product3);
        supplier2.addSupplyingProduct(product4);
        em.persist(product);
        em.persist(product2);
        em.persist(product3);
        em.persist(product4);
        Customer customer1 = new Customer("Hyzio", "Krakowska", "Warszawa", "00-001", 5),
                customer2 = new Customer("Dyzio","Lwowska", "Tarnów", "33-100", 10),
                customer3 = new Customer("Zyzio", "Reymonta", "Kraków", "02-456", 15);
        em.persist(customer1);
        em.persist(customer2);
        em.persist(customer3);

        etx.commit();

        TypedQuery<Supplier> q = em.createQuery("select sup from Supplier sup where sup.CompanyName = 'Jarek'", Supplier.class);
        System.out.println(q.getSingleResult());
        TypedQuery<Customer> q2 = em.createQuery("select cust from Customer cust where cust.CompanyName = 'Zyzio'", Customer.class);
        System.out.println(q2.getSingleResult());
        em.close();

    }
}
