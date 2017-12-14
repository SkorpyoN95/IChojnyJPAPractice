import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.metamodel.EntityType;

import java.util.Map;
import java.util.Scanner;

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
        String name, street, city;
        System.out.println("Put name, street and city of new supplier:");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        street = scanner.nextLine();
        city = scanner.nextLine();
        Supplier supplier = new Supplier(name, street, city);
        try {
            Transaction tx = session.beginTransaction();
            Product bread = session.get(Product.class, "Bread");
            bread.setSupplier(supplier);
            session.save(supplier);
            tx.commit();
        } finally {
            session.close();
        }
    }
}