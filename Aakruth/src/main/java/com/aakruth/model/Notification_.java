package com.aakruth.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Notification.class)
public abstract class Notification_ {

	public static volatile SingularAttribute<Notification, Date> dateTime;
	public static volatile SingularAttribute<Notification, String> action;
	public static volatile SingularAttribute<Notification, UsrTbl> usrTbl;
	public static volatile SingularAttribute<Notification, Integer> notId;

}

