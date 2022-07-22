package bg.lease.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="vendor")
public class VendorEntity {
    @Id
    @Size(max=20)
    @Column(name="No")
    private String no;

    @Size(max=100)
    @Column(name="Name")
    private String name;

    @Size(min=5,max=15)
    @Column(name="VAT_Registration")
    private String vatRegistration;

    @Size(max=100)
    @Column(name="Address")
    private String address;

    @ManyToOne
    private CountryEntity countryNo;

    @Size(max=30)
    @Column(name="City")
    private String city;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVatRegistration() {
        return vatRegistration;
    }

    public void setVatRegistration(String vatRegistration) {
        this.vatRegistration = vatRegistration;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CountryEntity getCountryNo() {
        return countryNo;
    }

    public void setCountryNo(CountryEntity countryNo) {
        this.countryNo = countryNo;
    }
}
