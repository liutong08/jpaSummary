# jpaSummary
jpaSummary

总结：

	1、映射
	
		通过Customers和Orders实体类来映射多对一的关系
		通过Teachers和Students实体类来映射多对多的关系
		通过Managers和Departments实体类来映射一对一多的关系
	
	2、查询
	
		1.通过find使用id查询
		
			entityManager.find(XXX.class, id);
			
		2.通过createNativeQuery使用?和index进行查询
		
			reateNativeQuery("select * from tablename where id = ?", XXX.class).setParameter(1, 2);
			
		3.通过实体类查询表中数据 setParameter顺序可乱
		
			①?序号
				entityManager.createQuery("select o from XXX o where o.id = ?序号", XXX.class).setParameter(序号,5).getSingleResult();
			
			②:别名
				entityManager.createQuery("select o from XXX o where o.id = :别名", XXX.class).setParameter("别名", 4).getSingleResult();
	
	3、删除

		1.通过find方法使用id找到该数据，并删除
		
			XXX xxx = entityManager.find(XXX.class, id);
			entityManager.remove(xxx);
			
		2.通过实体类删除表中数据 setParameter顺序可乱
		
			①?序号
				entityManager.createQuery("delete from XXX where id = ?序号").setParameter(序号, 7).executeUpdate();
			
			②:别名
				entityManager.createQuery("delete from XXX where id = :别名").setParameter("别名", 6).executeUpdate();
				
	4、更新
		
		通过实体类对对应数据进行修改 setParameter顺序可乱
		
			①?序号
				entityManager.createQuery("update XXX set name=?序号1 where id =?序号2").setParameter(序号1, "cc00").setParameter(序号2, 2).executeUpdate();
			②:别名
				entityManager.createQuery("update XXX set name= :别名1 where id = :别名2").setParameter("别名1", "cc000").setParameter("别名2", 3)
				.executeUpdate();
