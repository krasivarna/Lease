package bg.lease.model.dto;

import javax.validation.constraints.Size;

public class VendorDTO {
    private String no;
    private String name;
    @Size(min=5,max=15)
    private String vatRegistration;
    private String country;
    private String city;
    private String address;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public VendorDTO(){}
}
