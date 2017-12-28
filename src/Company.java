import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    protected String CompanyName;
    protected String street;
    protected String city;
    protected String zipCode;

    public Company(){}

    public Company(String companyName, String street, String city, String zipCode) {
        CompanyName = companyName;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    @Override
    public String toString(){
        return CompanyName + " | " + street + " " + city + " " + zipCode;
    }

}
