package cn.com.taiji;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Managers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "departments_id", referencedColumnName = "id") 
//	private Departments department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



//	public Departments getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(Departments department) {
//		this.department = department;
//	}

}
