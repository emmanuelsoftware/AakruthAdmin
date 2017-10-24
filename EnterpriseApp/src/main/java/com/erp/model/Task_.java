package com.erp.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Task.class)
public abstract class Task_ {

	public static volatile SingularAttribute<Task, Character> sta;
	public static volatile SingularAttribute<Task, String> taskDes;
	public static volatile SingularAttribute<Task, Date> enddate;
	public static volatile SingularAttribute<Task, Date> assignDate;
	public static volatile SingularAttribute<Task, String> assignRole;
	public static volatile SingularAttribute<Task, Integer> audUsrId;
	public static volatile SingularAttribute<Task, Date> startdate;
	public static volatile SingularAttribute<Task, User> user;
	public static volatile SingularAttribute<Task, Integer> taskId;

}

