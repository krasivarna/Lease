package bg.lease.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="country")
public class CountryEntity {
    @Id
    @Size(max=10)
    @Column(name="No")
    private String no;

    @Size(max=30)
    @Column(name="Name")
    private String name;

    public CountryEntity() {
    }

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
}
