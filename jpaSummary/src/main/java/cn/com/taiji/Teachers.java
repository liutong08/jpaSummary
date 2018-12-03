package cn.com.taiji;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Teachers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@ManyToMany
	@JoinTable(name = "teachers_students", joinColumns = @JoinColumn(name = "teachers_id"), inverseJoinColumns = @JoinColumn(name = "students_id"))
	private List<Students> studentList = new ArrayList<>();

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

	public List<Students> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Students> student) {
		this.studentList = student;
	}

}
