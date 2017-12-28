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
        em.persist(product);
        em.persist(product2);
        em.persist(product3);
        em.persist(product4);
        category.addProduct(product);
        category.addProduct(product2);
        category2.addProduct(product3);
        category2.addProduct(product4);
        Supplier supplier = new Supplier("Marek", "Krakowska", "Warszawa"),
                supplier2 = new Supplier("Darek", "Lwowska", "Tarnów");
        em.persist(supplier);
        em.persist(supplier2);
        supplier.addSupplyingProduct(product);
        supplier.addSupplyingProduct(product2);
        supplier2.addSupplyingProduct(product3);
        supplier2.addSupplyingProduct(product4);
        TTransaction trans1 = new TTransaction(),
                trans2 = new TTransaction(),
                trans3 = new TTransaction();
        em.persist(trans1);
        em.persist(trans2);
        em.persist(trans3);
        trans1.addProduct(product);
        trans1.addProduct(product);
        trans1.addProduct(product);
        trans1.addProduct(product3);
        trans2.addProduct(product2);
        trans2.addProduct(product2);
        trans2.addProduct(product2);
        trans2.addProduct(product2);
        trans3.addProduct(product4);
        trans3.addProduct(product4);
        trans3.addProduct(product3);
        trans3.addProduct(product3);
        trans3.addProduct(product);

        etx.commit();
        Query q = em.createQuery("select prod from Product as prod " +
                "join prod.transactions as trans where trans = :trans", Product.class).
                setParameter("trans", trans2);
        List<Product> l = q.getResultList();
        for(Product p : l){
            System.out.println(p);
        }
        Query q2 = em.createQuery("select trans from TTransaction as trans " +
                "join trans.Products as prod where prod = :prod", TTransaction.class).
                setParameter("prod", product);
        List<TTransaction> l2 = q2.getResultList();
        for(TTransaction t : l2){
            System.out.println(t);
        }
        em.close();

    }
}
