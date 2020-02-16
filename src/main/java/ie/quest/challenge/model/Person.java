package ie.quest.challenge.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "pps"))
public class Person {

    @Id
    private String pps;

    @Size(max=25)
    private String name;
    
    private String mobile;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    
    public Person() {
    }

	public Person(String pps, String name, String mobile, Date birth) {
		super();
		this.pps = pps;
		this.name = name;
		this.mobile = mobile;
		this.birth = birth;
	}

	public String getPps() {
		return pps;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public Date getBirth() {
		return birth;
	}

	public void setPps(String pps) {
		this.pps = pps;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Person [pps=" + pps + ", name=" + name + ", mobile=" + mobile
				+ ", birth=" + birth + "]";
	}

   
}
