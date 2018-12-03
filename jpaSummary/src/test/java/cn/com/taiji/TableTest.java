package cn.com.taiji;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TableTest {

	private static EntityManagerFactory factory = null;
	private static EntityManager entityManager = null;
	private static EntityTransaction transaction = null;

	@Before
	public void create() {
		factory = Persistence.createEntityManagerFactory("jpaSummary");
		entityManager = factory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}

	@After
	public void close() {
		transaction.commit();
		entityManager.close();
		factory.close();
	}

	// 通过find使用id查询
	@Test
	public void findByID() {
		Customers customer = entityManager.find(Customers.class, 2);
		System.out.println("customer--name:" + customer.getName());
	}

	// 通过createNativeQuery使用?和index进行查询
	@Test
	public void queryByIndex() {
		Query query = entityManager.createNativeQuery("select * from customers where id = ?", Customers.class)
				.setParameter(1, 2);
		Object singleResult = query.getSingleResult();
		System.out.println(singleResult);
	}

	// 通过实体类查询表中数据 ?序号
	@Test
	public void queryByEntity1() {
		Orders orders = entityManager.createQuery("select o from Orders o where o.id = ?1", Orders.class)
				.setParameter(1, 5).getSingleResult();
		System.out.println(orders);
	}

	// 通过实体类查询表中数据:别名
	@Test
	public void queryByEntity2() {
		Orders orders = entityManager.createQuery("select o from Orders o where o.id = :id", Orders.class)
				.setParameter("id", 4).getSingleResult();
		System.out.println(orders);
	}

	// 通过find方法使用id找到该数据，并删除
	@Test
	public void deleteByfind() {
		Orders order = entityManager.find(Orders.class, 3);
		entityManager.remove(order);
	}

	// 通过实体类删除表中数据 ?序号
	@Test
	public void deleteByEntity1() {
		int num = entityManager.createQuery("delete from Orders where id = ?1").setParameter(1, 7).executeUpdate();
		System.out.println(num);
	}

	// 通过实体类删除表中数据:别名
	@Test
	public void deleteByEntity2() {
		int num = entityManager.createQuery("delete from Orders where id = :id").setParameter("id", 6).executeUpdate();
		System.out.println(num);
	}

	// 通过实体类对对应数据进行修改 ?序号
	@Test
	public void updateByEntity1() {
		int num = entityManager.createQuery("update Customers set name=?1 where id =?2").setParameter(1, "cc00").setParameter(2, 2)
				.executeUpdate();
		System.out.println(num);
	}

	// 通过实体类对对应数据进行修改 :别名
	@Test
	public void updateByEntity2() {
		int num = entityManager.createQuery("update Customers set name= :name where id = :id").setParameter("name", "cc000").setParameter("id", 3)
				.executeUpdate();
		System.out.println(num);
	}

	// 多对一 添加数据
	@Test
	public void testManyToOne() {
		Customers customer = new Customers();
		customer.setName("cc01");

		Orders order1 = new Orders();
		order1.setName("oo01");

		Orders order2 = new Orders();
		order2.setName("oo02");

		customer.getOrdersList().add(order1);
		customer.getOrdersList().add(order2);

		order1.setCustomer(customer);
		order2.setCustomer(customer);

		entityManager.persist(customer);
		entityManager.persist(order1);
		entityManager.persist(order2);
	}

	// 多对多 添加数据
	@Test
	public void testManyToMany() {
		Teachers teacher1 = new Teachers();
		teacher1.setName("tea01");

		Teachers teacher2 = new Teachers();
		teacher2.setName("tea02");

		Students student1 = new Students();
		student1.setName("stu01");

		Students student2 = new Students();
		student2.setName("stu02");

		teacher1.getStudentList().add(student1);
		teacher1.getStudentList().add(student2);

		teacher2.getStudentList().add(student1);
		teacher2.getStudentList().add(student2);

		student1.getTeacherList().add(teacher1);
		student1.getTeacherList().add(teacher2);

		student2.getTeacherList().add(teacher1);
		student2.getTeacherList().add(teacher2);

		entityManager.persist(teacher1);
		entityManager.persist(teacher2);
		entityManager.persist(student1);
		entityManager.persist(student2);
	}

	// 一对一 添加数据
	@Test
	public void testOneToOne() {
		Managers manager = new Managers();
		manager.setName("mm01");

		Departments department = new Departments();
		department.setName("dd01");

		// manager.setDepartment(department);
		department.setManager(manager);

		// entityManager.persist(manager);
		entityManager.persist(department);
	}
}
