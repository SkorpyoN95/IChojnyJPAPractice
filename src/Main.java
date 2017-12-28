import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();
            Category category = new Category("Alcohol"),
                        category2 = new Category("Bakery");
            session.save(category);
            session.save(category2);
            Product product = new Product("Beer", 10),
                    product2 = new Product("Vodka", 5),
                    product3 = new Product("Bread", 15),
                    product4 = new Product("Bun", 20);
            session.save(product);
            session.save(product2);
            session.save(product3);
            session.save(product4);
            category.addProduct(product);
            category.addProduct(product2);
            category2.addProduct(product3);
            category2.addProduct(product4);
            Supplier supplier = new Supplier("Marek", "Krakowska", "Warszawa", "00-001", "1234567890"),
                    supplier2 = new Supplier("Darek","Lwowska", "Tarnów", "33-100", "0987654321");
            session.save(supplier);
            session.save(supplier2);
            supplier.addSupplyingProduct(product);
            supplier.addSupplyingProduct(product2);
            supplier2.addSupplyingProduct(product3);
            supplier2.addSupplyingProduct(product4);
            TTransaction trans1 = new TTransaction(),
                            trans2 = new TTransaction(),
                            trans3 = new TTransaction();
            session.save(trans1);
            session.save(trans2);
            session.save(trans3);
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
            tx.commit();
            Query<Product> q = session.createQuery("select prod from Product as prod " +
                                "join prod.transactions as trans where trans = :trans").
                                setParameter("trans", trans2);
            List<Product> l = q.list();
            for(Product p : l){
                System.out.println(p);
            }
            Query<TTransaction> q2 = session.createQuery("select trans from TTransaction as trans " +
                                    "join trans.Products as prod where prod = :prod").
                                    setParameter("prod", product);
            List<TTransaction> l2 = q2.list();
            for(TTransaction t : l2){
                System.out.println(t);
            }
        } finally {
            session.close();
        }
    }
}